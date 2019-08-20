package com.github.mavolin.maxon.utils;

import com.github.mavolin.maxon.jsonvalues.*;

import java.util.Map;

/**
 * The {@code JsonPrinter} class is a utility class, used to print JSONs in their {@link String String} representation
 * using a specific set of style options.
 */
public class JsonPrinter {


    private JsonPrinter() {
        // private to prevent instantiation
    }

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

    /**
     * Returns a {@link String String} printed with the data of the passed {@link JsonArray JsonArray} in the specified
     * style.
     *
     * @param jsonArray
     *         the {@link JsonArray JsonArray}
     * @param whitespaceChar
     *         the whitespace char
     * @param whitespaceCharQty
     *         the quantity of whitespace chars to use when producing indent
     * @param ignoreNull
     *         ignore {@code nulls}?
     * @param printStyle
     *         the {@link PrintStyle PrintStyle}
     *
     * @return the styled {@link JsonObject JsonObject}
     */
    public static String printJsonArray(JsonArray jsonArray, char whitespaceChar, int whitespaceCharQty,
                                        boolean ignoreNull, PrintStyle printStyle) {

        if (printStyle == PrintStyle.NO_WHITESPACE) {
            return printJsonArrayNoWhitespace(jsonArray, ignoreNull);
        } else if (printStyle == PrintStyle.SINGLE_WHITESPACE) {
            return printJsonArraySingleWhitespace(jsonArray, ignoreNull);
        } else {
            return printJsonArrayPrettyPrinted(jsonArray, whitespaceChar, whitespaceCharQty, ignoreNull, 0);
        }
    }

    /**
     * Returns a {@link String String} printed with the data of the passed {@link JsonObject JsonObject} in the
     * specified style.
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
     * @return the styled {@link JsonObject JsonObject}
     */
    public static String printJsonObject(JsonObject jsonObject, char whitespaceChar, int whitespaceCharQty,
                                         boolean ignoreNull, PrintStyle printStyle) {

        if (printStyle == PrintStyle.NO_WHITESPACE) {
            return printJsonObjectNoWhitespace(jsonObject, ignoreNull);
        } else if (printStyle == PrintStyle.SINGLE_WHITESPACE) {
            return printJsonObjectSingleWhitespace(jsonObject, ignoreNull);
        } else {
            return printJsonObjectPrettyPrinted(jsonObject, whitespaceChar, whitespaceCharQty, ignoreNull, 0);
        }
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

        arrayBuilder.append("]");

        return arrayBuilder.toString();
    }

    private static String printJsonArraySingleWhitespace(JsonArray jsonArray, boolean ignoreNull) {

        StringBuilder arrayBuilder = new StringBuilder("[");
        boolean first = true;

        for (JsonElement value : jsonArray) {
            if (value.isNull() && ignoreNull) {
                continue;
            }
            if (!first) {
                arrayBuilder.append(", ");
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

        arrayBuilder.append("]");

        return arrayBuilder.toString();
    }

    private static String printJsonArrayPrettyPrinted(JsonArray jsonArray, char whitespaceChar, int whitespaceCharQty,
                                                      boolean ignoreNull, int indentLvl) {

        String bracketIndent = generateIndent(whitespaceCharQty * indentLvl, whitespaceChar);
        String contentIndent = generateIndent(whitespaceCharQty * indentLvl + whitespaceCharQty, whitespaceChar);

        StringBuilder arrayBuilder = new StringBuilder("[\n" + contentIndent);
        boolean first = true;

        for (JsonElement value : jsonArray) {
            if (value.isNull() && ignoreNull) {
                continue;
            }
            if (!first) {
                arrayBuilder
                        .append(",\n")
                        .append(contentIndent);
            }

            JsonValue jsonValue = value.getAsJsonValue();
            String stringValue;

            if (jsonValue instanceof JsonPrimitive) {
                stringValue = printJsonPrimitive((JsonPrimitive) jsonValue);
            } else if (jsonValue instanceof JsonArray) {
                stringValue = printJsonArrayPrettyPrinted((JsonArray) jsonValue, whitespaceChar, whitespaceCharQty,
                                                          ignoreNull, indentLvl + 1);
            } else {
                stringValue = printJsonObjectPrettyPrinted((JsonObject) jsonValue, whitespaceChar, whitespaceCharQty,
                                                           ignoreNull, indentLvl + 1);
            }

            arrayBuilder.append(stringValue);

            first = false;
        }

        arrayBuilder
                .append("\n")
                .append(bracketIndent)
                .append("]");

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

    private static String printJsonObjectSingleWhitespace(JsonObject jsonObject, boolean ignoreNull) {

        StringBuilder objectBuilder = new StringBuilder("{");
        boolean first = true;

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (value.isNull() && ignoreNull) {
                continue;
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
                stringValue = printJsonArraySingleWhitespace((JsonArray) jsonValue, ignoreNull);
            } else {
                stringValue = printJsonObjectSingleWhitespace((JsonObject) jsonValue, ignoreNull);
            }
            objectBuilder.append(stringValue);

            first = false;
        }

        objectBuilder.append("}");

        return objectBuilder.toString();
    }

    private static String printJsonObjectPrettyPrinted(JsonObject jsonObject, char whitespaceChar,
                                                       int whitespaceCharQty, boolean ignoreNull, int indentLvl) {

        String bracketIndent = generateIndent(indentLvl * whitespaceCharQty, whitespaceChar);
        String contentIndent = generateIndent(indentLvl * whitespaceCharQty + whitespaceCharQty, whitespaceChar);

        StringBuilder objectBuilder = new StringBuilder("{\n" + contentIndent);
        boolean first = true;

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (value.isNull() && ignoreNull) {
                continue;
            }
            if (!first) {
                objectBuilder
                        .append(",\n")
                        .append(contentIndent);
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
                stringValue = printJsonArrayPrettyPrinted((JsonArray) jsonValue, whitespaceChar, whitespaceCharQty,
                                                          ignoreNull, indentLvl + 1);
            } else {
                stringValue = printJsonObjectPrettyPrinted((JsonObject) jsonValue, whitespaceChar, whitespaceCharQty,
                                                           ignoreNull, indentLvl + 1);
            }

            objectBuilder.append(stringValue);

            first = false;
        }

        objectBuilder
                .append("\n")
                .append(bracketIndent)
                .append("}");

        return objectBuilder.toString();
    }

    private static String generateIndent(int qty, char whitespaceChar) {

        return String.valueOf(whitespaceChar).repeat(qty);
    }


}
