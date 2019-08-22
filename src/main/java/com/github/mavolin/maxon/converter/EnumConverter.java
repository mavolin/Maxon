package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.Converts;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.convert.SerializedName;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.lang.reflect.Field;

/**
 * The {@code EnumConverter} is one of the two universal converters and is in charge of converting all enums. If an
 * enum field is annotated with a {@link com.github.mavolin.maxon.convert.SerializedName SerializedName} annotation,
 * it will use the provided name instead of the exact field name.
 */
@Converts(Enum.class)
public class EnumConverter implements JsonConverter {


    /**
     * Instantiates a new {@code EnumConverter}.
     */
    public EnumConverter() {

    }

    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * {@link Maxon Maxon}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     *
     * @return the converted {@link Object Object}
     */
    @Override
    public JsonValue getAsJson(Object source) {

        if (!(source instanceof Enum))
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");

        Enum enu = (Enum) source;

        String fieldName = enu.name();

        try {
            Field field = enu.getClass().getField(fieldName);

            boolean hasSerializedName = field.isAnnotationPresent(SerializedName.class);

            if (!hasSerializedName) {
                return new JsonPrimitive(fieldName);
            } else {
                SerializedName serializedName = field.getAnnotation(SerializedName.class);

                return new JsonPrimitive(serializedName.value());
            }
        } catch (NoSuchFieldException e) {

            // unreachable as we just extracted the correct name
            throw new JsonParsingException("An internal error occurred.");

        }
    }

    /**
     * Extracts information from the passed {@link JsonValue JsonValue} and builds a new {@link Object Object} of the
     * type {@code T} out of it.
     *
     * @param source
     *         the JSON representation of the {@link Object Object}
     * @param clazz
     *         the {@link Class Class} of the {@link Object Object}
     *
     * @return the extracted {@link Object Object}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(JsonValue source, Class<T> clazz) {

        if (!(source instanceof JsonPrimitive)) {
            throw new JsonParsingException("The provided JsonValue does not resemble a " + clazz.getName());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;


        if (!jsonPrimitive.isString()) {
            throw new JsonParsingException("The provided JsonValue does not resemble a " + clazz.getName());
        }
        String fieldName = jsonPrimitive.getAsString();

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(SerializedName.class)) {
                SerializedName serializedName = field.getAnnotation(SerializedName.class);

                if (serializedName.value().equals(fieldName)) {
                    try {
                        return (T) field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new JsonParsingException("Access control prevented getting the corresponding field", e);
                    }
                }
            } else {
                if (field.getName().equals(fieldName)) {
                    try {
                        return (T) field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new JsonParsingException("Access control prevented getting the corresponding field", e);
                    }
                }
            }
        }

        throw new JsonParsingException("The provided JsonValue does not resemble a field of the provided enum");
    }


}
