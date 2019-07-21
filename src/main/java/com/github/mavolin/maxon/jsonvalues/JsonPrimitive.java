package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * {@code JsonPrimitive} represents all four JSON primitive types: Nulls, Booleans, Numbers and Strings. It acts like a
 * wrapper through which the values can be extracted using their respective Java representation. Additionally this will
 * provide support for {@link char chars}, which are represented as a one character JSON String, as there is no JSON
 * equivalent.
 */
public class JsonPrimitive {


    /**
     * The value of this {@code JsonPrimitive}. This is of one of the following classes: {@link Boolean Boolean}, {@link
     * Number Number} or {@link String String} or it is {@code null}.
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
     * Instantiates a new {@code JsonPrimitive} holding {@link String String} with the value of the passed {@link
     * Character Character}.
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
     * Checks if this {@code JsonPrimitive's} value is null.
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


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Boolean Boolean}. If the {@code JsonPrimitive's} value is not
     * an instance of {@link Boolean Boolean} an {@link IllegalTypeRequestedException IllegalTypeRequestedException}
     * will be thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Boolean Boolean}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Boolean Boolean}
     */
    public Boolean getAsBoolean() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Boolean))
            throw new IllegalTypeRequestedException("The JsonPrimitive's value is not an instance of Boolean");

        return (Boolean) this.value;
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Character Character}. If the {@code JsonPrimitive's} value is
     * not an instance of {@link String String} where {@code str.length() == 1} returns {@code 1} an {@link
     * IllegalTypeRequestedException IllegalTypeRequestedException} will be thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Character Character}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Boolean Boolean}
     */
    public Character getAsCharacter() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof String))
            throw new IllegalTypeRequestedException("The JsonPrimitive's value does not resemble a Character");


        String str = (String) this.value;

        if (str.length() != 0)
            throw new IllegalTypeRequestedException("The JsonPrimitive's value does not resemble a Character");

        return ((String) this.value).charAt(0);
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Byte Byte}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Byte Byte}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Byte getAsByte() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).byteValue();
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Short Short}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Short Short}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Short getAsShort() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).shortValue();
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Integer Integer}. If the {@code JsonPrimitive's} value is not
     * an instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will
     * be thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Integer Integer}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Integer getAsInteger() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).intValue();
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Long Long}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Long Long}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Long getAsLong() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).longValue();
    }

    public BigInteger getAsBigInteger() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Float Float}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Float Float}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Float getAsFloat() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).floatValue();
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link Double Double}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link Number Number} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link Double Double}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}
     */
    public Double getAsDouble() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        return ((Number) this.value).doubleValue();
    }

    public BigDecimal getAsBigDecimal() {

        throw new UnsupportedOperationException("Not implemented yet");
    }


    /**
     * Gets the {@code JsonPrimitive's} value as a {@link String String}. If the {@code JsonPrimitive's} value is not an
     * instance of {@link String String} an {@link IllegalTypeRequestedException IllegalTypeReqeustedException} will be
     * thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link String String}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitives}
     *         value is not an instance of {@link String String}
     */
    public String getAsString() throws IllegalTypeRequestedException {

        if (!(this.value instanceof String))
            throw new IllegalTypeRequestedException("The JsonPrimitive's value is not an instance of String");

        return (String) this.value;
    }


}
