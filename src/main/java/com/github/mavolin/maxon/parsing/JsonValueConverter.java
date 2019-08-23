package com.github.mavolin.maxon.parsing;

import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

/**
 * The {@code JsonValueConverter} is the converter used by {@link com.github.mavolin.maxon.Maxon Maxon} to transform
 * {@link String Strings} of JSON data to their Java representations and vice-versa.
 */
public class JsonValueConverter {


    private static final String UNEXPECTED_TOKEN_ERR_TMPL = "Unexpected token '%s' at index %d";
    private static final String EXPECTED_CHAR_ERR_TMPL = "Expected '%s' but found '%s' at index %d";
    private static final String UNEXPECTED_CHAR_SET_ERR_TMPL = "Unexpected character set starting at index %d";


    /**
     * Converts a JSON value saved in the passed {@link String String} to its Java representation
     *
     * @param source
     *         the JSON value
     *
     * @return the Java representation of the JSON value
     */
    public JsonValue getFromJson(String source) {

        JsonTokener jsonTokener = new JsonTokener(source);
        jsonTokener.skipCommentAndWhitespace();

        JsonValue extractedValue = this.extract(jsonTokener);

        jsonTokener.skipCommentAndWhitespace();

        if (jsonTokener.hasNext()) {
            throw new JsonParsingException(String.format(UNEXPECTED_TOKEN_ERR_TMPL, jsonTokener.next(),
                                                         jsonTokener.getIndex()));
        }

        return extractedValue;
    }

    /**
     * Converts a JSON value saved in the passed {@link String String} to its Java representation
     *
     * @param <T>
     *         the type parameter
     * @param source
     *         the JSON value
     * @param clazz
     *         the {@link Class Class}
     *
     * @return the Java representation of the JSON value
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(String source, Class<T> clazz) {

        JsonTokener jsonTokener = new JsonTokener(source);
        jsonTokener.skipCommentAndWhitespace();

        JsonValue extractedValue;

        if (clazz.isAssignableFrom(JsonPrimitive.class)) {
            extractedValue = this.getJsonPrimitiveFromJson(jsonTokener);
        } else if (clazz.isAssignableFrom(JsonArray.class)) {
            extractedValue = this.getJsonArrayFromJson(jsonTokener);
        } else if (clazz.isAssignableFrom(JsonObject.class)) {
            extractedValue = this.getJsonObjectFromJson(jsonTokener);
        } else {
            throw new JsonParsingException("Unsupported class " + clazz.getName());
        }

        jsonTokener.skipCommentAndWhitespace();

        if (jsonTokener.hasNext()) {
            throw new JsonParsingException(String.format(UNEXPECTED_TOKEN_ERR_TMPL, jsonTokener.next(),
                                                         jsonTokener.getIndex()));
        }

        return (T) extractedValue;
    }


    /**
     * Extracts the element at the next position of the passed {@link JsonTokener JsonTokener} and returns it
     *
     * @param jsonTokener
     *         the {@link JsonTokener JsonTokener}
     *
     * @return the {@link JsonValue JsonValue}
     */
    private JsonValue extract(JsonTokener jsonTokener) {

        jsonTokener.skipCommentAndWhitespace();

        char next = jsonTokener.checkAndNextNoIncrement();

        switch (next) {
            case 't':
            case 'f':
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '"':
            case 'n':
                return this.getJsonPrimitiveFromJson(jsonTokener);
            case '[':
                return this.getJsonArrayFromJson(jsonTokener);
            case '{':
                return this.getJsonObjectFromJson(jsonTokener);
            default:
                throw new JsonParsingException(String.format(UNEXPECTED_TOKEN_ERR_TMPL, next,
                                                             jsonTokener.getIndex() + 1));
        }
    }

    /**
     * Extracts the JSON primitive beginning at the next char of the passed {@link JsonTokener JsonTokener} and returns
     * its Java representation.
     *
     * @param jsonTokener
     *         the {@link JsonTokener JsonTokener}
     *
     * @return the extracted {@link JsonPrimitive JsonPrimitive}
     */
    private JsonPrimitive getJsonPrimitiveFromJson(JsonTokener jsonTokener) {

        char next = jsonTokener.checkAndNextNoIncrement();

        switch (next) {
            case 't':
            case 'f':
                return new JsonPrimitive(jsonTokener.nextBoolean());
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return new JsonPrimitive(jsonTokener.nextNumber());
            case '"':
                return new JsonPrimitive(jsonTokener.nextString());
            case 'n':
                if (jsonTokener.next(4).equals("null")) {
                    return JsonPrimitive.NULL;
                } else {
                    throw new JsonParsingException(String.format(UNEXPECTED_CHAR_SET_ERR_TMPL,
                                                                 jsonTokener.getIndex() - 4));
                }
            default:
                throw new JsonParsingException(String.format(UNEXPECTED_TOKEN_ERR_TMPL, next,
                                                             jsonTokener.getIndex() + 1));
        }
    }


    /**
     * Extracts the JSON array beginning at the next char of the passed {@link JsonTokener JsonTokener} and returns its
     * Java representation.
     *
     * @param jsonTokener
     *         the {@link JsonTokener JsonTokener}
     *
     * @return the extracted {@link JsonArray JsonArray}
     */
    private JsonArray getJsonArrayFromJson(JsonTokener jsonTokener) {

        JsonArray jsonArray = new JsonArray();

        char next = jsonTokener.checkAndNext();
        boolean first = true;

        if (next != '[') {
            throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, '[', next, jsonTokener.getIndex()));
        }

        while (jsonTokener.hasNext()) {
            jsonTokener.skipCommentAndWhitespace();

            if (jsonTokener.nextNoIncrement() == ']') {
                jsonTokener.next();
                return jsonArray;
            }

            if (!first) {
                next = jsonTokener.checkAndNext();

                if (next != ',') {
                    throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, ',', next,
                                                                 jsonTokener.getIndex()));
                }

                jsonTokener.skipCommentAndWhitespace();
            }

            jsonArray.add(this.extract(jsonTokener));

            first = false;
        }

        throw new JsonParsingException("Unexpected end of JSON object");
    }

    /**
     * Extracts the JSON object beginning at the next char of the passed {@link JsonTokener JsonTokener} and returns its
     * Java representation.
     *
     * @param jsonTokener
     *         the {@link JsonTokener JsonTokener}
     *
     * @return the extracted {@link JsonObject JsonObject}
     */
    private JsonObject getJsonObjectFromJson(JsonTokener jsonTokener) {

        JsonObject jsonObject = new JsonObject();

        char next = jsonTokener.checkAndNext();
        boolean first = true;

        if (next != '{') {
            throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, '{', next, jsonTokener.getIndex()));
        }

        while (jsonTokener.hasNext()) {
            jsonTokener.skipCommentAndWhitespace();

            if (jsonTokener.nextNoIncrement() == '}') { // check for end of object
                jsonTokener.next();
                return jsonObject;
            }

            if (!first) { // if not first element expect a comma
                next = jsonTokener.checkAndNext();

                if (next != ',') {
                    throw new JsonParsingException(String.format(EXPECTED_CHAR_ERR_TMPL, ',', next,
                                                                 jsonTokener.getIndex()));
                }

                jsonTokener.skipCommentAndWhitespace();
            }

            String key = jsonTokener.nextString(); // extract the key

            jsonTokener.skipCommentAndWhitespace();

            next = jsonTokener.checkAndNext();
            if (next != ':') {
                throw new JsonParsingException(String.format(UNEXPECTED_TOKEN_ERR_TMPL, next, jsonTokener.getIndex()));
            }

            jsonTokener.skipCommentAndWhitespace();

            JsonValue value = this.extract(jsonTokener); // extract the value

            jsonObject.put(key, value);

            first = false;
        }

        throw new JsonParsingException("Unexpected end of JSON object");
    }


}
