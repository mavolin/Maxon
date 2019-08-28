package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

/**
 * The {@code UniversalObjectConverter} is used to provide a way to convert all {@link Object Objects} by using
 * Java's reflection api.
 */
public class UniversalObjectConverter implements JsonConverter {


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

        return null;
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
    public <T> T getFromJson(JsonValue source, Class<T> clazz) {

        return null;
    }


}
