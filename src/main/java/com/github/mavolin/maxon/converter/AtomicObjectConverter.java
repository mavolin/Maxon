package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.Converts;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.util.concurrent.atomic.*;

/**
 * The {@code AtomicObjectConverter} is used to provide a way of converting the different atomic numbers found in Java's
 * {@link java.util.concurrent java.util.concurrent} package.
 */
@Converts({AtomicBoolean.class, AtomicInteger.class, AtomicIntegerArray.class, AtomicLong.class, AtomicLongArray.class})
public class AtomicObjectConverter implements JsonConverter {


    private static final String PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS = "The provided JsonValue does not resemble a " +
                                                                         "%s";

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

        if (source instanceof AtomicBoolean) {
            return new JsonPrimitive(((AtomicBoolean) source).get());
        } else if (source instanceof AtomicInteger) {
            return new JsonPrimitive(((AtomicInteger) source).get());
        } else if (source instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) source;
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                jsonArray.add(atomicIntegerArray.get(i));
            }

            return jsonArray;
        } else if (source instanceof AtomicLong) {
            return new JsonPrimitive(((AtomicLong) source).get());
        } else if (source instanceof AtomicLongArray) {
            AtomicLongArray atomicLongArray = (AtomicLongArray) source;
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < atomicLongArray.length(); i++) {
                jsonArray.add(atomicLongArray.get(i));
            }

            return jsonArray;
        } else {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }
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

        if (AtomicBoolean.class.isAssignableFrom(clazz)) {
            return (T) this.getAtomicBooleanFromJson(source);
        } else if (AtomicInteger.class.isAssignableFrom(clazz)) {
            return (T) this.getAtomicIntegerFromJson(source);
        } else if (AtomicIntegerArray.class.isAssignableFrom(clazz)) {
            return (T) this.getAtomicIntegerArrayFromJson(source);
        } else if (AtomicLong.class.isAssignableFrom(clazz)) {
            return (T) this.getAtomicLongFromJson(source);
        } else if (AtomicLongArray.class.isAssignableFrom(clazz)) {
            return (T) this.getAtomicLongArrayFromJson(source);
        } else {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }
    }

    /**
     * Extracts a {@link AtomicBoolean AtomicBoolean} from the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representation of the {@link AtomicBoolean AtomicBoolean}
     *
     * @return the extracted {@link AtomicBoolean AtomicBoolean}
     */
    private AtomicBoolean getAtomicBooleanFromJson(JsonValue source) {

        if (!(source instanceof JsonPrimitive)) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicBoolean"));
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;

        if (jsonPrimitive.isNull()) {
            return null;
        }
        if (!jsonPrimitive.isBoolean()) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicBoolean"));
        }

        return new AtomicBoolean(jsonPrimitive.getAsBoolean());
    }

    /**
     * Extracts a {@link AtomicInteger AtomicInteger} from the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representation of the {@link AtomicInteger AtomicInteger}
     *
     * @return the extracted {@link AtomicInteger AtomicInteger}
     */
    private AtomicInteger getAtomicIntegerFromJson(JsonValue source) {

        if (!(source instanceof JsonPrimitive)) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicInteger"));
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;

        if (jsonPrimitive.isNull()) {
            return null;
        }
        if (!jsonPrimitive.isNumber()) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicInteger"));
        }

        return new AtomicInteger(jsonPrimitive.getAsInteger());
    }

    /**
     * Extracts a {@link AtomicIntegerArray AtomicIntegerArray} from the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representation of the {@link AtomicIntegerArray AtomicIntegerArray}
     *
     * @return the extracted {@link AtomicIntegerArray AtomicIntegerArray}
     */
    private AtomicIntegerArray getAtomicIntegerArrayFromJson(JsonValue source) {

        if (!(source instanceof JsonArray)) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicIntegerArray"));
        }
        JsonArray jsonArray = (JsonArray) source;

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(jsonArray.size());

        for (int i = 0; i < jsonArray.size(); i++) {
            atomicIntegerArray.set(i, jsonArray.getAsInteger(i));
        }

        return atomicIntegerArray;
    }

    /**
     * Extracts a {@link AtomicLong AtomicLong} from the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representation of the {@link AtomicLong AtomicLong}
     *
     * @return the extracted {@link AtomicLong AtomicLong}
     */
    private AtomicLong getAtomicLongFromJson(JsonValue source) {

        if (!(source instanceof JsonPrimitive)) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicLong"));
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;

        if (jsonPrimitive.isNull()) {
            return null;
        }
        if (!jsonPrimitive.isNumber()) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicLong"));
        }

        return new AtomicLong(jsonPrimitive.getAsLong());
    }

    /**
     * Extracts a {@link AtomicLongArray AtomicLongArray} from the passed {@link JsonValue JsonValue}.
     *
     * @param source
     *         the JSON representation of the {@link AtomicLongArray AtomicLongArray}
     *
     * @return the extracted {@link AtomicLongArray AtomicLongArray}
     */
    private AtomicLongArray getAtomicLongArrayFromJson(JsonValue source) {

        if (!(source instanceof JsonArray)) {
            throw new IllegalTypeRequestedException(String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS,
                                                                  "AtomicIntegerArray"));
        }
        JsonArray jsonArray = (JsonArray) source;

        AtomicLongArray atomicLongArray = new AtomicLongArray(jsonArray.size());

        for (int i = 0; i < jsonArray.size(); i++) {
            atomicLongArray.set(i, jsonArray.getAsLong(i));
        }

        return atomicLongArray;
    }


}
