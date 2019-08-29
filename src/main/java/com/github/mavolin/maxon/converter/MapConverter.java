package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The {@code MapConverter} is used to convert {@link Map Maps} into JSON objects and vice versa.
 */
public class MapConverter {


    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * {@link Maxon Maxon}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     *
     * @return the converted {@link Object Object}
     */
    public JsonValue getAsJson(Object source, Maxon maxon) {

        if (!(source instanceof Map)) {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }
        Map map = (Map) source;

        JsonObject mapObject = new JsonObject();
        JsonArray mapArray = new JsonArray();
        AtomicBoolean first = new AtomicBoolean(true);

        map.forEach((key, value) -> {
            if (first.get()) {
                first.set(false);
                mapObject
                        .put("keyClass", key.getClass().getName())
                        .put("valueClass", key.getClass().getName());
            }

            JsonObject mapEntry = new JsonObject();
            mapEntry
                    .put("key", maxon.getAsJsonValue(key))
                    .put("value", maxon.getAsJsonValue(value));

            mapArray.add(mapEntry);
        });

        mapObject.put("map", mapArray);

        return mapObject;
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
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(JsonValue source, Class<T> clazz, Maxon maxon) {

        if (!(source instanceof JsonObject)) {
            throw new JsonParsingException(clazz.getName() + " is not convertible with this converter");
        }
        JsonObject jsonMap = (JsonObject) source;

        String keyClassString = jsonMap.getAsString("keyClass");
        String valueClassString = jsonMap.getAsString("valueClass");
        JsonArray arrayMap = jsonMap.getAsJsonArray("map");

        Class<?> keyClass;
        Class<?> valueClass;
        try {
            keyClass = Class.forName(keyClassString);
        } catch (ClassNotFoundException e) {
            throw new JsonParsingException("Could not find the class of the key", e);
        }
        try {
            valueClass = Class.forName(valueClassString);
        } catch (ClassNotFoundException e) {
            throw new JsonParsingException("Could not find the class of the key", e);
        }


        Map map;

        if (HashMap.class.isAssignableFrom(clazz)) {
            map = new HashMap();
        } else if (LinkedHashMap.class.isAssignableFrom(clazz)) {
            return (T) this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass);
        } else if (Hashtable.class.isAssignableFrom(clazz)) {
            map = new Hashtable();
        } else if (IdentityHashMap.class.isAssignableFrom(clazz)) {
            map = new IdentityHashMap();
        } else if (TreeMap.class.isAssignableFrom(clazz)) {
            map = new TreeMap();
        } else if (WeakHashMap.class.isAssignableFrom(clazz)) {
            map = new WeakHashMap();
        } else if (ConcurrentHashMap.class.isAssignableFrom(clazz)) {
            map = new ConcurrentHashMap();
        } else if (ConcurrentSkipListMap.class.isAssignableFrom(clazz)) {
            map = new ConcurrentSkipListMap();
        } else if (EnumMap.class.isAssignableFrom(clazz)) {
            if (!Enum.class.isAssignableFrom(keyClass)) {
                throw new JsonParsingException("Expected Enum as key class for EnumMap, but found " + keyClassString);
            }

            map = new EnumMap(keyClass);
        } else {
            throw new JsonParsingException(clazz.getName() + " is not convertible with this converter");
        }

        map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

        return (T) map;
    }

    @SuppressWarnings("unchecked")
    private Map getLinkedHashMapFromJson(JsonArray jsonMap, Maxon maxon, Class keyClass, Class valueClass) {

        Map<Object, Object> map = new LinkedHashMap<>();

        jsonMap.forEach(element -> {
            JsonObject entry = element.getAsJsonObject();
            Object key = maxon.getFromJson(entry.getAsJsonValue("key"), keyClass);
            Object value = maxon.getFromJson(entry.getAsJsonValue("value"), valueClass);

            map.put(key, value);
        });

        return map;
    }


}
