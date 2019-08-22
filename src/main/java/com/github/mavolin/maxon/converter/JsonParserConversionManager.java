package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.convert.JsonParser;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonParserConversionManager implements JsonConverter {


    /**
     * The registered {@link JsonParser JsonParser}.
     */
    private final Map<Class<?>, JsonParser<?>> parser = new HashMap<>();

    /**
     * Instantiates a new {@code JsonParserConversionHandler}.
     */
    public JsonParserConversionManager() {

    }


    /**
     * Registers a {@link JsonParser JsonParser} with this {@code JsonParserConversionHandler} instance.
     *
     * @param <T>
     *         the type parameter
     * @param jsonParser
     *         the {@link JsonParser JsonParser}
     * @param clazz
     *         the {@link Class Class} of the {@link Object Object} the {@link JsonParser JsonParser} produces
     */
    public <T> void registerParser(JsonParser<T> jsonParser, Class<T> clazz) {

        Objects.requireNonNull(jsonParser, "The provided JsonParser is null");
        Objects.requireNonNull(clazz, "The provided Class is null");

        this.parser.put(clazz, jsonParser);
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
    @SuppressWarnings("unchecked")
    public JsonValue getAsJson(Object source) {

        Class<?> clazz = source.getClass();

        if (!this.parser.containsKey(clazz)) {
            throw new JsonParsingException("There is no JsonParser registered for the specified class");
        }

        JsonParser jsonParser = this.parser.get(clazz);

        return jsonParser.getAsJson(source);
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

        if (!this.parser.containsKey(clazz)) {
            throw new JsonParsingException("There is no JsonParser registered for the specified class");
        }

        JsonParser jsonParser = this.parser.get(clazz);

        return (T) jsonParser.getFromJson(source);
    }


}
