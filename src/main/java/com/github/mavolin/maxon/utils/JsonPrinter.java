package com.github.mavolin.maxon.utils;

import com.github.mavolin.maxon.jsonvalues.*;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code JsonPrinter} class is a utility class, used to print JSONs in their {@link String String} representation
 * using a specific set of style options.
 */
public class JsonPrinter {


    /**
     * Returns the {@link JsonPrimitive JsonPrimitve} in its JSON form.
     *
     * @param jsonPrimitive
     *         the {@link JsonPrimitive JsonPrimitive}
     *
     * @return the JSON {@link String String}
     */
    public static String printJsonPrimitive(JsonPrimitive jsonPrimitive) {

        if (jsonPrimitive.isNull()) {
            return "null";
        } else if (jsonPrimitive.isBoolean()) {
            return jsonPrimitive.getAsBoolean().toString();
        } else if (jsonPrimitive.isCharacter()) {
            String character = String.valueOf(jsonPrimitive.getAsCharacter());

            return "\"" + character
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t")
                   + "\"";
        } else if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsBigDecimal().toString();
        } else {
            String json = jsonPrimitive.getAsString();

            return "\"" + json
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t")
                   + "\"";
        }
    }

    public static String printJsonArray(JsonArray jsonArray, char whitespaceChar, int whitespaceCharQty,
                                        boolean ignoreNull, PrintStyle printStyle) {
        return null;
    }

    /**
     * Returns a {@link JsonObject JsonObject} printed with the specified style.
     *
     * @param jsonObject
     *         the {@link JsonObject JsonObject}
     * @param whitespaceChar
     *         the whitespace char
     * @param whitespaceCharQty
     *         the quantity of whitespace chars to use when producing indent
     * @param ignoreNull
     *         ignore {@code nulls}?
     * @param printStyle
     *         the {@link PrintStyle PrintStyle}
     *
     * @return the style {@link JsonObject JsonObject}
     */
    public static String printJsonObject(JsonObject jsonObject, char whitespaceChar, int whitespaceCharQty,
                                         boolean ignoreNull, PrintStyle printStyle) {

        if (printStyle == PrintStyle.NO_WHITESPACE)
            return printJsonObjectNoWhitespace(jsonObject, ignoreNull);
        else if (printStyle == PrintStyle.SINGLE_WHITESPACE)
            return printJsonObjectSingleWhitespace(jsonObject, whitespaceChar, ignoreNull);
    }

    private static String printJsonArrayNoWhitespace(JsonArray jsonArray, boolean ignoreNull) {

        StringBuilder arrayBuilder = new StringBuilder("[");
        boolean first = true;

        for (JsonElement value : jsonArray) {
            if (value.isNull() && ignoreNull) {
                continue;
            }
            if (!first) {
                arrayBuilder.append(",");
            }

            JsonValue jsonValue = value.getAsJsonValue();
            String stringValue;

            if (jsonValue instanceof JsonPrimitive) {
                stringValue = printJsonPrimitive((JsonPrimitive) jsonValue);
            } else if (jsonValue instanceof JsonArray) {
                stringValue = printJsonArrayNoWhitespace((JsonArray) jsonValue, ignoreNull);
            } else {
                stringValue = printJsonObjectNoWhitespace((JsonObject) jsonValue, ignoreNull);
            }
            arrayBuilder.append(stringValue);

            first = false;
        }

        arrayBuilder.append("[");

        return arrayBuilder.toString();
    }

    private static String printJsonObjectNoWhitespace(JsonObject jsonObject, boolean ignoreNull) {

        StringBuilder objectBuilder = new StringBuilder("{");
        boolean first = true;

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (value.isNull() && ignoreNull) {
                continue;
            }
            if (!first) {
                objectBuilder.append(",");
            }

            objectBuilder
                    .append("\"")
                    .append(key)
                    .append("\":");

            JsonValue jsonValue = value.getAsJsonValue();
            String stringValue;

            if (jsonValue instanceof JsonPrimitive) {
                stringValue = printJsonPrimitive((JsonPrimitive) jsonValue);
            } else if (jsonValue instanceof JsonArray) {
                stringValue = printJsonArrayNoWhitespace((JsonArray) jsonValue, ignoreNull);
            } else {
                stringValue = printJsonObjectNoWhitespace((JsonObject) jsonValue, ignoreNull);
            }
            objectBuilder.append(stringValue);

            first = false;
        }

        objectBuilder.append("}");

        return objectBuilder.toString();
    }

    private static String printJsonObjectSingleWhitespace(JsonObject jsonObject, char whitespaceChar,
                                                          boolean ignoreNull) {
        StringBuilder objectBuilder = new StringBuilder("{");
        boolean first = true;

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (value.isNull() && ignoreNull) {
                break;
            }
            if (!first) {
                objectBuilder.append(", ");
            }

            objectBuilder
                    .append("\"")
                    .append(key)
                    .append("\": ");

            JsonValue jsonValue = value.getAsJsonValue();
            String stringValue;

            if (jsonValue instanceof JsonPrimitive) {
                stringValue = printJsonPrimitive((JsonPrimitive) jsonValue);
            } else if (jsonValue instanceof JsonArray) {
                stringValue = printJsonArrayNoWhitespace((JsonArray) jsonValue, ignoreNull);
            } else {
                stringValue = printJsonObjectNoWhitespace((JsonObject) jsonValue, ignoreNull);
            }
            objectBuilder.append(stringValue);

            first = false;
        }

        objectBuilder.append("}");

        return objectBuilder.toString();
    }


}
