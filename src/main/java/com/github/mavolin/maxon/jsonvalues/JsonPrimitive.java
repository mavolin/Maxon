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


    /**
     * Checks if this {@code JsonPrimitives} value is null.
     *
     * @return {@code true} if this {@code JsonPrimitives} value is null; {@code false} otherwise
     */
    public boolean isNull() {

        return this.value == null;
   }

    /**
     * Checks if this {@code JsonPrimitive} represents a Boolean.
     *
     * @return {@code true} if this {@code JsonPrimitive} represents a Boolean; {@code false} otherwise
     */
    public boolean isBoolean() {

        return this.value instanceof Boolean;
    }

    /**
     * Checks if this {@code JsonPrimitive} represents a Character.
     *
     * @return {@code true} if this {@code JsonPrimitive} represents a Character; {@code false} otherwise
     */
    public boolean isCharacter() {

        return this.value instanceof Character;
    }

    /**
     * Checks if this {@code JsonPrimitive} represents a Number.
     *
     * @return {@code true} if this {@code JsonPrimitive} represents a Number; {@code false} otherwise
     */
    public boolean isNumber() {

        return this.value instanceof Number;
    }


    /**
     * Checks if this {@code JsonPrimitive} represents a String.
     *
     * @return {@code true} if this {@code JsonPrimitive} represents a String; {@code false} otherwise
     */
    public boolean isString() {

        return this.value instanceof String;
    }


    public Boolean getAsBoolean() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Character getAsCharacter() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Byte getAsByte() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Short getAsShort() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Integer getAsInteger() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Long getAsLong() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    public Float getAsFloat() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


}
