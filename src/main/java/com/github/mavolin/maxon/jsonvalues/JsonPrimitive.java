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
     *         value is not an instance of {@link Character Character}
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
     *         value is not an instance of {@link Number Number}, if the dimensions of the number exceed those of {@link
     *         Byte Byte} or the requested number is decimal
     */
    public Byte getAsByte() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal byteMin = BigDecimal.valueOf(Byte.MIN_VALUE);
        BigDecimal byteMax = BigDecimal.valueOf(Byte.MAX_VALUE);

        if (num.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
            throw new IllegalTypeRequestedException("The requested number is a decimal number");

        if (num.compareTo(byteMin) < 0 || num
                .compareTo(byteMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");

        return num.byteValue();
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
     *         value is not an instance of {@link Number Number}, if the dimensions of the number exceed those of {@link
     *         Short Short} or the requested number is decimal
     */
    public Short getAsShort() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal shortMin = BigDecimal.valueOf(Short.MIN_VALUE);
        BigDecimal shortMax = BigDecimal.valueOf(Short.MAX_VALUE);

        if (num.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
            throw new IllegalTypeRequestedException("The requested number is a decimal number");

        if (num.compareTo(shortMin) < 0 || num
                .compareTo(shortMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");

        return num.shortValue();
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
     *         value is not an instance of {@link Number Number}, if the dimensions of the number exceed those of {@link
     *         Integer Integer} or the requested number is decimal
     */
    public Integer getAsInteger() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal intMin = BigDecimal.valueOf(Integer.MIN_VALUE);
        BigDecimal intMax = BigDecimal.valueOf(Integer.MAX_VALUE);

        if (num.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
            throw new IllegalTypeRequestedException("The requested number is a decimal number");

        if (num.compareTo(intMin) < 0 || num
                .compareTo(intMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");

        return num.intValue();
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
     *         value is not an instance of {@link Number Number}, if the dimensions of the number exceed those of {@link
     *         Long Long} or the requested number is decimal
     */
    public Long getAsLong() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal longMin = BigDecimal.valueOf(Long.MIN_VALUE);
        BigDecimal longMax = BigDecimal.valueOf(Long.MAX_VALUE);

        if (num.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
            throw new IllegalTypeRequestedException("The requested number is a decimal number");

        if (num.compareTo(longMin) < 0 || num
                .compareTo(longMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");

        return num.longValue();
    }

    /**
     * Gets the {@code JsonPrimitive's} value as a {@link BigInteger BigInteger}. If the {@code JsonPrimitive's} value
     * is not an instance of {@link Number Number} an {@link IllegalTypeRequestedException
     * IllegalTypeReqeustedException} will be thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link BigInteger BigInteger}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number}, if the dimensions of the number exceed those of {@link
     *         BigInteger BigInteger} or the requested number is decimal
     */
    public BigInteger getAsBigInteger() {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal longMin = BigDecimal.valueOf(Long.MIN_VALUE);
        BigDecimal longMax = BigDecimal.valueOf(Long.MAX_VALUE);

        if (num.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
            throw new IllegalTypeRequestedException("The requested number is a decimal number");

        if (num.compareTo(longMin) < 0 || num
                .compareTo(longMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");


        return num.toBigInteger();
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
     *         value is not an instance of {@link Number Number} or if the dimensions of the number exceed those of
     *         {@link Byte Byte}
     */
    public Float getAsFloat() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal floatMin = BigDecimal.valueOf(Float.MIN_VALUE);
        BigDecimal floatMax = BigDecimal.valueOf(Float.MAX_VALUE);

        if (num.compareTo(floatMin) < 0 || num
                .compareTo(floatMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");


        return num.floatValue();
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
     *         value is not an instance of {@link Number Number} or if the dimensions of the number exceed those of
     *         {@link Byte Byte}
     */
    public Double getAsDouble() throws IllegalTypeRequestedException {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal doubleMin = BigDecimal.valueOf(Double.MIN_VALUE);
        BigDecimal doubleMax = BigDecimal.valueOf(Double.MAX_VALUE);

        if (num.compareTo(doubleMin) < 0 || num
                .compareTo(doubleMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");


        return num.doubleValue();
    }

    /**
     * Gets the {@code JsonPrimitive's} value as a {@link BigDecimal BigDecimal}. If the {@code JsonPrimitive's} value
     * is not an instance of {@link Number Number} an {@link IllegalTypeRequestedException
     * IllegalTypeReqeustedException} will be thrown.
     *
     * @return the {@code JsonPrimitive's} value as a {@link BigDecimal BigDecimal}
     *
     * @throws IllegalTypeRequestedException
     *         an {@link IllegalTypeRequestedException IllegalTypeRequestedException} if the {@code JsonPrimitive's}
     *         value is not an instance of {@link Number Number} or if the dimensions of the number exceed those of
     *         {@link BigInteger BigInteger}
     */
    public BigDecimal getAsBigDecimal() {

        if (this.value == null)
            return null;

        if (!(this.value instanceof Number))
            throw new IllegalArgumentException("The JsonPrimitive's value is not an instance of Number");

        BigDecimal num = new BigDecimal(this.value.toString());

        BigDecimal doubleMin = BigDecimal.valueOf(Double.MIN_VALUE);
        BigDecimal doubleMax = BigDecimal.valueOf(Double.MAX_VALUE);

        if (num.compareTo(doubleMin) < 0 || num
                .compareTo(doubleMax) > 0) // test if num exceeds the dimensions of the requested type
            throw new IllegalTypeRequestedException(
                    "The dimensions of the requested number exceed those of the requested class");


        return num;
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
