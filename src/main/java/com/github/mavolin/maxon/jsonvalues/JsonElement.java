package com.github.mavolin.maxon.jsonvalues;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.BooleanSupplier;

/**
 * A {@code JsonElement} is the Object used by {@link JsonArray JsonArrays} and {@link JsonObject JsonObjects} in their
 * respective collections, to make the use of the enhanced for loop more easy to use. To do so {@code JsonElement}
 * contains {@code getAsX()} methods to prevent the need of casting on the user end.
 */
// TODO add reference to JsonParser
public class JsonElement {


    /**
     * The value of the {@code JsonElement}.
     */
    private JsonValue value;


    public JsonElement(JsonValue value) {

        this.value = value;
    }


    public BooleanSupplier getAsBoolean() {

        throw new UnsupportedOperationException("Not implemented yet");
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


}
