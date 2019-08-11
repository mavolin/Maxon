package com.github.mavolin.maxon.convert;

import com.github.mavolin.maxon.jsonvalues.JsonElement;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

/**
 * A {@code JsonConverter} is an interface used for all converters.
 */
public interface JsonConverter {


    /**
     * Converts the passed {@link Object Object} to a {@link JsonValue JsonValue}, which is then processed further by
     * the JSONCONVERTER.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     *
     * @return the converted {@link Object Object}
     */
    // TODO add reference to converter object
    JsonValue getAsJson(Object source);

    /**
     * Extracts information from the passed {@link JsonElement JsonElement} and builds a new {@link Object Object} of
     * the type {@code T} out of it
     *
     * @param <T>
     *         the type of the {@link Object Object} that is to be produced
     * @param source
     *         the JSON representation of the {@link Object Object}
     * @param origin
     *         the {@link Class Class} of the {@link Object Object}
     *
     * @return the extracted {@link Object Object}
     */
    <T> T getFromJson(JsonElement source, Class<T> origin);


}
