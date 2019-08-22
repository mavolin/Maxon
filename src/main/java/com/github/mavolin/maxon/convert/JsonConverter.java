package com.github.mavolin.maxon.convert;

import com.github.mavolin.maxon.jsonvalues.JsonValue;

/**
 * A {@code JsonConverter} is an interface used for all converters.
 */
public interface JsonConverter {


    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * {@link com.github.mavolin.maxon.Maxon Maxon}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     *
     * @return the converted {@link Object Object}
     */
    JsonValue getAsJson(Object source);

    /**
     * Extracts information from the passed {@link JsonValue JsonValue} and builds a new {@link Object Object} of the
     * type {@code T} out of it.
     *
     * @param <T>
     *         the type of the {@link Object Object} that is to be produced
     * @param source
     *         the JSON representation of the {@link Object Object}
     * @param clazz
     *         the {@link Class Class} of the {@link Object Object}
     *
     * @return the extracted {@link Object Object}
     */
    <T> T getFromJson(JsonValue source, Class<T> clazz);


}
