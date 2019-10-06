package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.List;
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
        AtomicBoolean first  = new AtomicBoolean(false);


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


}
