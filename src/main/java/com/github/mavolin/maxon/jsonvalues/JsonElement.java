package com.github.mavolin.maxon.jsonvalues;

/**
 * A {@code JsonElement} is the Object used by {@link JsonArray JsonArrays} and {@link JsonObject JsonObjects} in their
 * respective collections, to make the use of the enhanced for loop more easy to use. To do so, a {@code JsonElement}
 * contains all getAsX() methods, so that in the for loop, ugly castings are needed.
 */
// TODO add reference to JsonParser
public class JsonElement {


    /**
     * The value of the {@code JsonElement}.
     */
    private JsonValue value;


}
