package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.Converts;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonElement;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The {@code PrimitivesConverter} converts all JSON primitive to JSON, as well as from JSON to their respective Java
 * representation. This converter therefore converts all Java primitive types, {@link BigInteger BigIntegers}, {@link
 * BigDecimal BigDecimals} and {@link String Strings}.
 */
@Converts({boolean.class, Boolean.class, char.class, Character.class, byte.class, Byte.class, short.class,
                  Short.class, int.class, Integer.class, long.class, Long.class, float.class, Float.class,
                  double.class, Double.class, BigInteger.class, BigDecimal.class, String.class})
public class PrimitivesConverter implements JsonConverter {


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

        if (source instanceof Boolean) {
            return new JsonPrimitive((Boolean) source);
        } else if (source instanceof Character) {
            return new JsonPrimitive((Character) source);
        } else if (source instanceof Byte || source instanceof Short || source instanceof Integer ||
                   source instanceof Long || source instanceof Float || source instanceof Double ||
                   source instanceof BigInteger || source instanceof BigDecimal) {
            return new JsonPrimitive((Number) source);
        } else if (source instanceof String) {
            return new JsonPrimitive((String) source);
        } else {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }
    }

    /**
     * Extracts information from the passed {@link JsonElement JsonElement} and builds a new {@link Object Object} of
     * the type {@code T} out of it.
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

        if (!(source instanceof JsonPrimitive)) {
            throw new IllegalTypeRequestedException(
                    String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;

        if (Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isBoolean()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, "Boolean"));
            }
            return (T) jsonPrimitive.getAsBoolean();
        } else if (Character.class.isAssignableFrom(clazz) || char.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isCharacter()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, "Character"));
            }
            return (T) jsonPrimitive.getAsCharacter();
        } else if (Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, "Byte"));
            }
            return (T) jsonPrimitive.getAsByte();
        } else if (Short.class.isAssignableFrom(clazz) || short.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, "Short"));
            }
            return (T) jsonPrimitive.getAsShort();
        } else if (Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsInteger();
        } else if (Long.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsLong();
        } else if (Float.class.isAssignableFrom(clazz) || float.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsFloat();
        } else if (Double.class.isAssignableFrom(clazz) || double.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsDouble();
        } else if (BigInteger.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsBigInteger();
        } else if (BigDecimal.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isNumber()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsBigDecimal();
        } else if (String.class.isAssignableFrom(clazz)) {
            if (!jsonPrimitive.isString()) {
                throw new IllegalTypeRequestedException(
                        String.format(PROVIDED_JSON_VALUE_NOT_RESEMBLE_CLASS, clazz.getName()));
            }
            return (T) jsonPrimitive.getAsString();
        } else {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible with this converter");
        }
    }


}
