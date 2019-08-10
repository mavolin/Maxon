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

    public Character getAsCharacter() {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Short getAsByte() {

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
