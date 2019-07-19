package com.github.mavolin.jsonvalues;

/**
 * {@code JsonNull} is the Java representation of the JSON value null.
 */
public class JsonNull extends JsonValue {


    /**
     * Get the Java representation of JSON null.
     *
     * @return {@code null}
     */
    public Object get() {

        return null;
    }

    /**
     * Gets the raw version of {@code JsonNull}.
     *
     * @return the raw JSON
     */
    @Override
    public String getRaw() {

        return "null";
    }

    /**
     * Gets pretty printed version of {@code JsonNull}. Same as {@link #getRaw()}.
     *
     * @return the pretty printed JSON
     */
    @Override
    public String getPrettyPrinted() {

        return "null";
    }


}
