package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.AbortOnMissingField;
import com.github.mavolin.maxon.convert.DeserializationConstructor;
import com.github.mavolin.maxon.convert.Serialize;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code UniversalObjectConverter} is used to provide a way to convert all {@link Object Objects} by using Java's
 * reflection api.
 */
public class UniversalObjectConverter {


    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * {@link Maxon Maxon}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     * @param maxon
     *         the {@link Maxon Maxon} converter
     *
     * @return the converted {@link Object Object}
     */
    public JsonValue getAsJson(Object source, Maxon maxon) {

        Class<?> clazz = source.getClass();
        JsonObject jsonObject = new JsonObject();

        Map<String, Field> extractFields = getExtractFields(clazz);

        for (Map.Entry<String, Field> entry : extractFields.entrySet()) {
            String key = entry.getKey();
            Field value = entry.getValue();

            try {
                boolean accessible = value.canAccess(source);
                value.setAccessible(true);

                Object valueValue = value.get(source);

                value.setAccessible(accessible);

                JsonValue valueAsJson = maxon.getAsJsonValue(valueValue);
                jsonObject.put(key, valueAsJson);
            } catch (IllegalAccessException e) {
                throw new JsonParsingException("Java access control prevented access", e);
            }
        }

        return jsonObject;
    }

    /**
     * Extracts information from the passed {@link JsonValue JsonValue} and builds a new {@link Object Object} of the
     * type {@code T} out of it.
     *
     * @param <T>
     *         the type parameter
     * @param source
     *         the JSON representation of the {@link Object Object}
     * @param clazz
     *         the {@link Class Class} of the {@link Object Object}
     * @param maxon
     *         the {@link Maxon Maxon} converter
     *
     * @return the extracted {@link Object Object}
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(JsonValue source, Class<T> clazz, Maxon maxon) {

        if (!(source instanceof JsonObject)) {
            throw new JsonParsingException("The provided JsonValue does not resemble an Object");
        }
        JsonObject jsonObject = (JsonObject) source;

        T object = null;
        boolean globalAbortOnMissing;

        Map<String, Field> extractFields = getExtractFields(
                clazz); // contains the name of the field as found in the JSON object and the field itself

        AbortOnMissingField globalAbortOnMissingField = clazz.getDeclaredAnnotation(AbortOnMissingField.class);
        if (globalAbortOnMissingField != null) {
            globalAbortOnMissing = globalAbortOnMissingField.value();
        } else {
            globalAbortOnMissing = false;
        }

        for (Constructor constructor : clazz.getDeclaredConstructors()) { // find the correct annotated constructor and use
            // it to initialize object
            DeserializationConstructor deserializationConstructor = constructor.getDeclaredAnnotation(
                    DeserializationConstructor.class);

            if (deserializationConstructor != null) {
                String[] fieldNames = deserializationConstructor.value();

                Object[] initArgs = new Object[fieldNames.length];
                Class[] parameterTypes = constructor.getParameterTypes();

                for (int i = 0; i < fieldNames.length; i++) {
                    String fieldName = fieldNames[i];

                    if (!jsonObject.has(fieldName)) {
                        throw new JsonParsingException(
                                "There is no field named \"" + fieldName + "\" in the passed JSON");
                    }

                    initArgs[i] = maxon.getFromJson(jsonObject.getAsJsonValue(fieldName), parameterTypes[i]);

                    extractFields.remove(fieldName);
                }

                try {
                    boolean accessible = constructor.canAccess(null);
                    constructor.setAccessible(true);

                    object = (T) constructor.newInstance(initArgs);

                    constructor.setAccessible(accessible);
                } catch (InstantiationException e) {
                    throw new JsonParsingException("The underlying class is abstract", e);
                } catch (IllegalAccessException e) {
                    throw new JsonParsingException("Java Access control prevented instantiation", e);
                } catch (InvocationTargetException e) {
                    throw new JsonParsingException("The constructor has thrown an exception", e);
                }
            }
        }

        if (object == null) { // if no constructor is annotated with DeserializationConstructor...
            try {
                Constructor<T> constructor = clazz.getDeclaredConstructor();

                boolean accessible = constructor.canAccess(null);
                constructor.setAccessible(true);

                object = constructor.newInstance(); // ... try to use the no-arg constructor

                constructor.setAccessible(accessible);
            } catch (InstantiationException e) {
                throw new JsonParsingException("The underlying class is abstract", e);
            } catch (IllegalAccessException e) {
                throw new JsonParsingException("Java Access control prevented instantiation of the object", e);
            } catch (InvocationTargetException e) {
                throw new JsonParsingException("The constructor has thrown an exception", e);
            } catch (NoSuchMethodException e) {
                throw new JsonParsingException("No matching method found", e);
            }
        }

        for (Map.Entry<String, Field> entry : extractFields.entrySet()) {
            String key = entry.getKey();
            Field value = entry.getValue();

            if (!jsonObject.has(key)) {
                AbortOnMissingField abortOnMissingField = value.getDeclaredAnnotation(AbortOnMissingField.class);
                if (abortOnMissingField == null) {
                    if (globalAbortOnMissing) {
                        throw new JsonParsingException(
                                "The passed JsonValue does not contain a field named \"" + key + "\"");
                    } else {
                        continue;
                    }
                } else if (abortOnMissingField.value()) {
                    throw new JsonParsingException(
                            "The passed JsonValue does not contain a field named \"" + key + "\"");
                } else {
                    continue;
                }
            }

            boolean accessible = value.canAccess(object);
            value.setAccessible(true);

            Object newFieldValue = maxon.getFromJson(jsonObject.getAsJsonValue(key), value.getType());

            try {
                value.set(object, newFieldValue);
            } catch (IllegalAccessException e) {
                throw new JsonParsingException("Java Access control prevented the field from being assigned a value");
            }

            value.setAccessible(accessible);
        }

        return object;
    }

    /**
     * Returns a map populated with the fields that are included in the JSON object, with their JSON name as key.
     *
     * @param clazz
     *         The {@link Class Class} of the {@link Field Fields}
     *
     * @return a map populated with the fields that are included in the JSON object, with their JSON name as key
     */
    private Map<String, Field> getExtractFields(Class clazz) {

        Map<String, Field> extractFields = new LinkedHashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                Serialize serialize = field.getDeclaredAnnotation(Serialize.class);

                if (serialize != null) {
                    String serializeValue = serialize.value();

                    if (serializeValue.equals("[USE FIELD NAME]")) {
                        serializeValue = field.getName();
                    }

                    if (extractFields.containsKey(serializeValue)) {
                        throw new JsonParsingException("Duplicate name \"" + serializeValue + "\" found");
                    }

                    extractFields.put(serializeValue, field);
                }
            }
        }

        if (extractFields.isEmpty()) { // if no field was annotated deserialize all fields
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName();

                    if (extractFields.containsKey(name)) {
                        throw new JsonParsingException("Duplicate name \"" + name + "\" found");
                    }

                    extractFields.put(name, field);
                }
            }
        }

        return extractFields;
    }


}
