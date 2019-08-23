package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.convert.ObjectConverter;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code ObjectConverterManager} manages all {@link ObjectConverter ObjectConverter} that are registered for a
 * specific {@link Maxon Maxon}.
 */
public class ObjectConverterManager implements JsonConverter {


    /**
     * The registered {@link ObjectConverter ObjectConverter}.
     */
    private final Map<Class<?>, ObjectConverter<?>> converter = new HashMap<>();

    /**
     * Instantiates a new {@code ObjectConverterManager}.
     */
    public ObjectConverterManager() {

    }


    /**
     * Registers a {@link ObjectConverter ObjectConverter} with this {@code ObjectConverterManager} instance.
     *
     * @param <T>
     *         the type parameter
     * @param objectConverter
     *         the {@link ObjectConverter ObjectConverter}
     * @param clazz
     *         the {@link Class Class} of the {@link Object Object} the {@link ObjectConverter ObjectConverter}
     *         produces
     */
    public <T> void registerParser(ObjectConverter<T> objectConverter, Class<T> clazz) {

        Objects.requireNonNull(objectConverter, "The provided ObjectConverter is null");
        Objects.requireNonNull(clazz, "The provided Class is null");

        this.converter.put(clazz, objectConverter);
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

        if (!this.converter.containsKey(clazz)) {
            throw new JsonParsingException("There is no ObjectConverter registered for the specified class");
        }

        ObjectConverter objectConverter = this.converter.get(clazz);

        return objectConverter.getAsJson(source);
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

        if (!this.converter.containsKey(clazz)) {
            throw new JsonParsingException("There is no ObjectConverter registered for the specified class");
        }

        ObjectConverter objectConverter = this.converter.get(clazz);

        return (T) objectConverter.getFromJson(source);
    }


}
