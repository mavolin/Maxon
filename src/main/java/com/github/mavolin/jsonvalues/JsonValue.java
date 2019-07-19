package com.github.mavolin.jsonvalues;

/**
 * The abstract {@code JsonValue} class represents all JSON values.
 */
public abstract class JsonValue {


    /**
     * Gets the raw version of the {@code JsonValue}. This will create a short, but not always easy to read String with
     * the contents of the respective {@code JsonValue}.
     *
     * @return the raw
     */
    public abstract String getRaw();

    /**
     * Gets a pretty printed version of the {@code JsonValue}. While creating the pretty printed version, the focus
     * is lied on creating a easily readable JSON.
     *
     * @return the pretty printed JSON
     */
    public abstract String getPrettyPrinted();

}
