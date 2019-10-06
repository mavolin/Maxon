package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The {@code MapConverter} is used to convert {@link List Lists} into JSON objects and vice versa.
 */
public class ListConverter {


    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * {@link Maxon Maxon}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     * @param maxon
     *         the {@link Maxon Maxon} object used to convert the elements of the map
     *
     * @return the as json
     */
    public JsonValue getAsJson(Object source, Maxon maxon) {

        if (!(source instanceof List)) {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }

        List list = (List) source;

        JsonObject arrayObject = new JsonObject();
        JsonArray array = new JsonArray();
        AtomicBoolean first  = new AtomicBoolean(true);

        list.forEach(item -> {
            if (first.get()) {
                first.set(false);
                arrayObject.put("itemClass", item.getClass().getName());
            }

            array.add(maxon.getAsJsonValue(item));
        });

        arrayObject.put("array", array);

        return arrayObject;
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
     *         the {@link Maxon Maxon} object used to convert the elements of the map
     *
     * @return the extracted {@link Object Object}
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(JsonValue source, Class<T> clazz, Maxon maxon) {

        if (!(source instanceof JsonObject)) {
            throw new JsonParsingException(clazz.getName() + " is not convertible with this converter");
        }
        JsonObject jsonMap = (JsonObject) source;

        String itemClassString = jsonMap.getAsString("itemClass");
        JsonArray array = jsonMap.getAsJsonArray("map");

        Class<?> type;
        try {
            type = Class.forName(itemClassString);
        } catch (ClassNotFoundException e) {
            throw new JsonParsingException("Could not find the class of the key", e);
        }

        List list;

        if (ArrayList.class.isAssignableFrom(clazz)) {
            list = new ArrayList();
        } else if (LinkedList.class.isAssignableFrom(clazz)) {
            list = new LinkedList();
        } else if (Vector.class.isAssignableFrom(clazz)) {
            list = new Vector();
        } else if (Stack.class.isAssignableFrom(clazz)) {
            list = new Stack();
        } else {
            throw new JsonParsingException(clazz.getName() + " is not convertible with this converter");
        }

        list.addAll(this.getLinkedListFromJson(array, maxon, type));

        return (T) list;
    }

    @SuppressWarnings("unchecked")
    private List getLinkedListFromJson(JsonArray array, Maxon maxon, Class type) {

        List<Object> list = new LinkedList<>();

        array.forEach(element ->
            list.add(maxon.getFromJson(element.getAsJsonValue(), type)));

        return list;
    }


}
