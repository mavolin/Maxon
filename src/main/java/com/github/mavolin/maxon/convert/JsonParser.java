package com.github.mavolin.maxon.convert;

import com.github.mavolin.maxon.jsonvalues.JsonValue;

/**
 * A {@code JsonParser} is a more "compact" version of the {@link JsonConverter JsonConverter}. In contrast to the
 * {@link JsonConverter JsonConverter}, the {@code JsonParser} can only convert only {@link Object Objects} of one type
 * {@code T}. This makes it ideal, to implement in self-written classes.
 *
 * @param <T>
 *         the type this {@code JsonParser} converts from and to
 */
public interface JsonParser<T> {


    /**
     * Converts the {@link Object Object} of the type {@code T} to Json.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     *
     * @return the {@link Object Object} in its JSON form
     */
    JsonValue getAsJson(T source);

    /**
     * Extracts the {@link Object Object} out of the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representing the {@link Object Object}
     *
     * @return the extracted {@link Object}
     */
    T getFromJson(JsonValue source);


}
