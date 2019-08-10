package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A {@code JsonElement} is the Object used by {@link JsonArray JsonArrays} and {@link JsonObject JsonObjects} in their
 * respective collections, to make the use of the enhanced for loop more easy to use. To do so {@code JsonElement}
 * contains {@code getAsX()} methods to prevent the need of casting on the user end.
 */
public class JsonElement {


    /**
     * The value of the {@code JsonElement}.
     */
    private JsonValue value;


    /**
     * Creates a new {@code JsonElement} holding the passed {@link JsonValue JsonValue}.
     *
     * @param value
     *         the value
     */
    public JsonElement(JsonValue value) {

        this.value = value;
    }


    /**
     * Gets this {@code JsonElement} as a {@link Boolean Boolean}.
     *
     * @return the {@link Boolean Boolean}
     *
     * @throws IllegalTypeRequestedException
     *         if this {@code JsonElement's} value is not a {@code Boolean}
     */
    public Boolean getAsBoolean() throws IllegalTypeRequestedException {

        if (!(this.value instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Boolean");

        JsonPrimitive bool = (JsonPrimitive) this.value;

        if (!bool.isBoolean() && !bool.isNull())
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Boolean");

        return bool.getAsBoolean();
    }

    /**
     * Gets this {@code JsonElement} as a {@link Character Character}.
     *
     * @return the {@link Character Character}
     *
     * @throws IllegalTypeRequestedException
     *         if this {@code JsonElement's} value is not a {@code Character}
     */
    public Character getAsCharacter() {

        if (!(this.value instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Character");

        JsonPrimitive character = (JsonPrimitive) this.value;

        if (!character.isCharacter() && !character.isNull())
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Character");

        return character.getAsCharacter();
    }

    /**
     * Gets this {@code JsonElement} as a {@link Byte Byte}.
     *
     * @return the {@link Byte Byte}
     *
     * @throws IllegalTypeRequestedException
     *         if this {@code JsonElement's} value is not a {@code Byte}
     */
    public Byte getAsByte() {

        if (!(this.value instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Byte");

        JsonPrimitive bytee = (JsonPrimitive) this.value;

        if (!bytee.isCharacter() && !bytee.isNull())
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Byte");

        return bytee.getAsByte();
    }

    /**
     * Gets this {@code JsonElement} as a {@link Short Short}.
     *
     * @return the {@link Short Short}
     *
     * @throws IllegalTypeRequestedException
     *         if this {@code JsonElement's} value is not a {@code Short}
     */
    public Short getAsShort() {

        if (!(this.value instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Short");

        JsonPrimitive shortt = (JsonPrimitive) this.value;

        if (!shortt.isCharacter() && !shortt.isNull())
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Short");

        return shortt.getAsShort();
    }

    /**
     * Gets this {@code JsonElement} as an {@link Integer Integer}.
     *
     * @return the {@link Integer Integer}
     *
     * @throws IllegalTypeRequestedException
     *         if this {@code JsonElement's} value is not an instance of {@code Integer}
     */
    public Integer getAsInteger() {

        if (!(this.value instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Integer");

        JsonPrimitive shortt = (JsonPrimitive) this.value;

        if (!shortt.isCharacter() && !shortt.isNull())
            throw new IllegalTypeRequestedException("The value of this JsonElement is not a Integer");

        return shortt.getAsInteger();
    }

    public Long getAsLong() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public BigInteger getAsBigInteger() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Double getAsFloat() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Double getAsDouble() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public BigDecimal getAsBigDecimal() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getAsString() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public <T> T getAsObject(Class<T> clazz) {

        throw new UnsupportedOperationException("Not implemented yet"); // TODO add
    }


}
