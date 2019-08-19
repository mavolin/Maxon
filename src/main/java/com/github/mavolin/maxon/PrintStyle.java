package com.github.mavolin.maxon;

/**
 * The enum {@code PrintStyle} defines the style of the produce JSON String.
 */
public enum PrintStyle {


    /**
     * <p>
     *     Produces a JSON with no whitespace between the different elements.
     * </p>
     * <p>
     *     Example:<br>
     *     <code>
     *         {"firstKey":true,"secondKey":123,"lastKey":"Hello World!"}
     *     </code>
     * </p>
     */
    NO_WHITESPACE,
    /**
     * <p>
     *     Produces a JSON with one whitespace between the different elements.
     * </p>
     * <p>
     *     Example:<br>
     *     <code>
     *         {"firstKey": true, "secondKey":123, "lastKey": "Hello World!"}
     *     </code>
     * </p>
     */
    SINGLE_WHITESPACE,
    /**
     * <p>
     *     Produces a pretty-printed JSON with whitespace between the different elements and a linebreak after each
     *     element with the elements being indented.
     * </p>
     * <p>
     *     Example:<br>
     *     <code>
     *          {<br>
     *              &nbsp&nbsp&nbsp&nbsp"firstKey": true,<br>
     *              &nbsp&nbsp&nbsp&nbsp"secondKey": 123,<br>
     *              &nbsp&nbsp&nbsp&nbsp"lastKey": "Hello World!"<br>
     *         }
     * </code>
     * </p>
     */
    PRETTY_PRINTED

}
