package com.github.mavolin.maxon.objects;

/**
 * The abstract class {@code JsonValue} is extended by all those objects, which resemble a JSON value.
 */
public abstract class JsonValue {


    /**
     * Gets the raw JSON. This means that the return value of this method will be a JSON representation of the
     * {@code JsonValue} that is short and does not attach any value to human readability.
     *
     * @return the raw JSON
     */
    public abstract String getRaw();

    /**
     * Gets the JSON pretty printed. This method provides a String representation of this JSON, that is easy to read
     * by a human.
     *
     * @return the pretty printed JSON
     */
    public abstract String getPrettyPrinted();


}
