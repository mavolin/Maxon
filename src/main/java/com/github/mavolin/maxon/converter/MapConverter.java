package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.Converts;
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
@Converts({HashMap.class, Hashtable.class, EnumMap.class, IdentityHashMap.class, LinkedHashMap.class, Properties.class,
                  TreeMap.class, WeakHashMap.class,
                  ConcurrentHashMap.class, ConcurrentSkipListMap.class})
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

        Class<?> keyClass = null;
        Class<?> valueClass = null;
        try {
            keyClass = Class.forName(keyClassString);
        } catch (ClassNotFoundException e) {
            throw new JsonParsingException("Could not find the class of the key", e);
        }
        try {
            valueClass = Class.forName(keyClassString);
        } catch (ClassNotFoundException e) {
            throw new JsonParsingException("Could not find the class of the key", e);
        }


        if (HashMap.class.isAssignableFrom(clazz)) {
            HashMap map = new HashMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));
            
            return (T) map;
        } else if (LinkedHashMap.class.isAssignableFrom(clazz)) {
            return (T) this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass);
        } else if (Hashtable.class.isAssignableFrom(clazz)) {
            Hashtable map = new Hashtable();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));
            
            return (T) map;
        } else if (IdentityHashMap.class.isAssignableFrom(clazz)) {
            IdentityHashMap map = new IdentityHashMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else if (TreeMap.class.isAssignableFrom(clazz)) {
            TreeMap map = new TreeMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else if (WeakHashMap.class.isAssignableFrom(clazz)) {
            WeakHashMap map = new WeakHashMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else if (ConcurrentHashMap.class.isAssignableFrom(clazz)) {
            ConcurrentHashMap map = new ConcurrentHashMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else if (ConcurrentSkipListMap.class.isAssignableFrom(clazz)) {
            ConcurrentSkipListMap map = new ConcurrentSkipListMap();
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else if (EnumMap.class.isAssignableFrom(clazz)) {
            if (!Enum.class.isAssignableFrom(keyClass)) {
                throw new JsonParsingException("Expected Enum as key class for EnumMap, but found " + keyClassString);
            }

            EnumMap map = new EnumMap(keyClass);
            map.putAll(this.getLinkedHashMapFromJson(arrayMap, maxon, keyClass, valueClass));

            return (T) map;
        } else {
            throw new JsonParsingException(clazz.getName() + " is not convertible with this converter");
        }
    }

    public Map getLinkedHashMapFromJson(JsonArray jsonMap, Maxon maxon, Class keyClass, Class valueClass) {

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
