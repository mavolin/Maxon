package com.github.mavolin.maxon.jsonvalues;

/**
 * {@code JsonPrimitive} represents all four JSON primitive types: Nulls, Booleans, Numbers and Strings. It acts like
 * a wrapper through which the values can be extracted using their respective Java representation. Additionally this
 * will provide for {@link char chars}, by which are represented as a one character JSON String.
 */
public class JsonPrimitive {


    /**
     * The value of this {@code JsonPrimitive}. This is of one of the following classes: {@link Boolean Boolean},
     * {@link Number Number} or {@link String String} or it is {@code null}.
     */
    private final Object value;


    /**
     * Instantiates a new {@code JsonPrimitive} holding a {@link Boolean Boolean} value.
     *
     * @param bool
     *         the {@code Boolean Boolean}
     */
    public JsonPrimitive(Boolean bool) {

        this.value = bool;
    }

    /**
     * Instantiates a new {@code JsonPrimitive} holding {@link Character Character} value.
     *
     * @param character
     *         the {@link Character Character}
     */
    public JsonPrimitive(Character character) {

        this.value = String.valueOf(character);
    }

    /**
     * Instantiates a new {@code JsonPrimitive} holding a {@link Number Number} value.
     *
     * @param number
     *         the number
     */
    public JsonPrimitive(Number number) {

        this.value = number;
    }

    /**
     * Instantiates a new {@code JsonPrimitive} holding a {@link String String} value.
     *
     * @param string
     *         the {@link String String}
     */
    public JsonPrimitive(String string) {

        this.value = string;
    }


    public boolean isNull() {
        throw new UnsupportedOperationException("Not implemented yet");
   }

    public boolean isBoolean() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isCharacter() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isNumber() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public boolean isString() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


}
