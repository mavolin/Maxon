package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A {@code JsonArray} is a list-like representation of an JSON array. In contrast to arrays found in Java, a JSON
 * array, is not limited to a list of objects of a specific type. This means, that the same JSON array may hold numbers
 * and strings.
 */
public class JsonArray implements JsonValue, Iterable<JsonElement> {


    private static final String INVALID_TYPE_REQUEST_ERR_TMPL = "The element at the index %d does not resemble a %s";
    /**
     * Holds the fields of the {@code JsonArray}.
     */
    private List<JsonElement> fields = new ArrayList<>();


    /**
     * Instantiates an empty {@code JsonArray}.
     */
    public JsonArray() {

    }

    /**
     * Adds the passed {@link Boolean Boolean} to the {@code JsonArray}.
     *
     * @param bool
     *         the {@link Boolean Boolean} that is to be added
     *
     * @return itself
     */
    public JsonArray add(Boolean bool) {

        this.add(new JsonPrimitive(bool));

        return this;
    }

    /**
     * Adds the passed {@link Character Character} to the {@code JsonArray}.
     *
     * @param character
     *         the {@link Character Character} that is to be added
     *
     * @return itself
     */
    public JsonArray add(Character character) {

        this.add(new JsonPrimitive(character));

        return this;
    }

    /**
     * Adds the passed {@link Number Number} to the {@code JsonArray}.
     *
     * @param num
     *         the {@link Number Number} that is to be added
     *
     * @return itself
     */
    public JsonArray add(Number num) {

        this.add(new JsonPrimitive(num));

        return this;
    }

    /**
     * Adds the passed {@link String String} to the {@code JsonArray}.
     *
     * @param string
     *         the {@link String String} that is to be added
     *
     * @return itself
     */
    public JsonArray add(String string) {

        this.add(new JsonPrimitive(string));

        return this;
    }

    /**
     * Adds the passed {@link JsonValue JsonValue} to the {@code JsonArray}.
     *
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to be added
     *
     * @return itself
     */
    public JsonArray add(JsonValue jsonValue) {

        this.fields.add(new JsonElement(jsonValue));

        return this;
    }


    /**
     * Adds the passed {@link Boolean Boolean} at the specified index. This results in all other elements for which
     * {@code element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link Boolean Boolean} is to be added to
     * @param bool
     *         the {@link Boolean Boolean} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, Boolean bool) {

        this.add(index, new JsonPrimitive(bool));

        return this;
    }

    /**
     * Adds the passed {@link Character Character} at the specified index. This results in all other elements for which
     * {@code element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link Character Character} is to be added to
     * @param character
     *         the {@link Character Character} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, Character character) {

        this.add(index, new JsonPrimitive(character));

        return this;
    }

    /**
     * Adds the passed {@link Number Number} at the specified index. This results in all other elements for which {@code
     * element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link Number Number} is to be added to
     * @param num
     *         the {@link Number Number} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, Number num) {

        this.add(index, new JsonPrimitive(num));

        return this;
    }

    /**
     * Adds the passed {@link String String} at the specified index. This results in all other elements for which {@code
     * element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link String String} is to be added to
     * @param string
     *         the {@link String String} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, String string) {

        this.add(index, new JsonPrimitive(string));

        return this;
    }

    /**
     * Adds the passed {@link JsonValue JsonValue} at the specified index. This results in all other elements for which
     * {@code element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link JsonValue JsonValue} is to be added to
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, JsonValue jsonValue) {

        return this.add(index, new JsonElement(jsonValue));
    }

    /**
     * Adds the passed {@link JsonElement JsonElement} at the specified index. This results in all other elements for
     * which {@code element.index >= index} would be true, to be shifted one index up.
     *
     * @param index
     *         the index the {@link JsonElement JsonElement} is to be added to
     * @param jsonElement
     *         the {@link JsonElement JsonElement} that is to be added
     *
     * @return itself
     */
    public JsonArray add(int index, JsonElement jsonElement) {

        this.fields.add(index, Objects.requireNonNullElse(jsonElement, new JsonElement(null)));

        return this;
    }

    /**
     * Adds all fields stored in the passed {@code JsonArray} to itself.
     *
     * @param jsonArray
     *         the {@code JsonArray}
     */
    public void addAll(JsonArray jsonArray) {

        this.fields.addAll(jsonArray.fields);
    }


    /**
     * Replaces the element at the specified index with the passed {@link Boolean Boolean}. Returns the element that was
     * previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param bool
     *         the {@link Boolean Boolean} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, Boolean bool) {

        return this.set(index, new JsonPrimitive(bool));
    }

    /**
     * Replaces the element at the specified index with the passed {@link Character Character}. Returns the element that
     * was previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param character
     *         the {@link Character Character} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, Character character) {

        return this.set(index, new JsonPrimitive(character));
    }

    /**
     * Replaces the element at the specified index with the passed {@link Number Number}. Returns the element that was
     * previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param num
     *         the {@link Number Number} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, Number num) {

        return this.set(index, new JsonPrimitive(num));
    }

    /**
     * Replaces the element at the specified index with the passed {@link String String}. Returns the element that was
     * previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param string
     *         the {@link String String} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, String string) {

        return this.set(index, new JsonPrimitive(string));
    }

    /**
     * Replaces the element at the specified index with the passed {@link JsonValue JsonValue}. Returns the element that
     * was previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, JsonValue jsonValue) {

        return this.set(index, new JsonElement(jsonValue));
    }

    /**
     * Replaces the element at the specified index with the passed {@link JsonElement JsonElement}. Returns the element that
     * was previously saved at the specified index.
     *
     * @param index
     *         the index
     * @param jsonElement
     *         the {@link JsonElement JsonElement} that is to replace the element at the specified index
     *
     * @return a {@link JsonElement JsonElement} representing the element that was previously stored at the specified
     * index
     */
    public JsonElement set(int index, JsonElement jsonElement) {

        return this.fields.set(index, Objects.requireNonNullElse(jsonElement, new JsonElement(null)));
    }


    /**
     * Gets the {@link Boolean Boolean} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Boolean Boolean}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Boolean Boolean}
     */
    public Boolean getAsBoolean(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Boolean"));

        return jsonElement.getAsBoolean();
    }


    /**
     * Gets the {@link Character Character} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Character Character}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Character Character}
     */
    public Character getAsCharacter(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Character"));

        return jsonElement.getAsCharacter();
    }

    /**
     * Gets the {@link Byte Byte} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Byte Byte}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Byte Byte}
     */
    public Byte getAsByte(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Byte"));

        return jsonElement.getAsByte();
    }

    /**
     * Gets the {@link Short Short} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Short Short}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Short Short}
     */
    public Short getAsShort(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Short"));

        return jsonElement.getAsShort();
    }

    /**
     * Gets the {@link Integer Integer} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Integer Integer}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Integer Integer}
     */
    public Integer getAsInteger(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Integer"));

        return jsonElement.getAsInteger();
    }

    /**
     * Gets the {@link Long Long} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Long Long}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Long Long}
     */
    public Long getAsLong(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Long"));

        return jsonElement.getAsLong();
    }

    /**
     * Gets the {@link java.math.BigInteger java.math.BigInteger} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link java.math.BigInteger java.math.BigInteger}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link BigInteger BigInteger}
     */
    public BigInteger getAsBigInteger(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "BigInteger"));

        return jsonElement.getAsBigInteger();
    }

    /**
     * Gets the {@link Float Float} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Float Float}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Float Float}
     */
    public Float getAsFloat(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Float"));

        return jsonElement.getAsFloat();
    }

    /**
     * Gets the {@link Double Double} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link Double Double}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link Double Double}
     */
    public Double getAsDouble(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "Double"));

        return jsonElement.getAsDouble();
    }

    /**
     * Gets the {@link java.math.BigDecimal java.math.BigDecimal} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link java.math.BigDecimal java.math.BigDecimal}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link java.math.BigDecimal
     *         java.math.BigDecimal}
     */
    public BigDecimal getAsBigDecimal(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (!jsonElement.isNumber() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "BigDecimal"));

        return jsonElement.getAsBigDecimal();
    }

    /**
     * Gets the {@link String String} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link String String}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link String String}
     */
    public String getAsString(int index) {

        JsonElement jsonElement = this.fields.get(index);

       if (!jsonElement.isString() && !jsonElement.isNull())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "String"));

        return jsonElement.getAsString();
    }

    /**
     * Gets the {@link JsonArray JsonArray} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link JsonArray JsonArray}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link JsonArray JsonArray}
     */
    public JsonArray getAsJsonArray(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (jsonElement.isJsonArray())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "JsonArray"));

        return jsonElement.getAsJsonArray();
    }

    /**
     * Gets the {@link JsonObject JsonObject} at the specified index.
     *
     * @param index
     *         the index of the requested element
     *
     * @return the {@link JsonObject JsonObject}
     *
     * @throws IllegalTypeRequestedException
     *         if the element at the specified index is not an instance of {@link JsonObject JsonObject}
     */
    public JsonObject getAsJsonObject(int index) {

        JsonElement jsonElement = this.fields.get(index);

        if (jsonElement.isJsonObject())
            throw new IllegalTypeRequestedException(String.format(INVALID_TYPE_REQUEST_ERR_TMPL, index, "JsonObject"));

        return jsonElement.getAsJsonObject();
    }


    /**
     * Removes the element at the specified position in this {@code JsonArray} (optional operation). Shifts any
     * subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the
     * list.
     *
     * @param index
     *         the index of the element to be removed
     *
     * @return the element previously at the specified position
     *
     * @throws UnsupportedOperationException
     *         if the {@code remove} operation is not supported by this list
     * @throws IndexOutOfBoundsException
     *         if the index is out of range ({@code index < 0 || index >= size()})
     */
    public JsonElement remove(int index) {

        return this.fields.remove(index);
    }

    /**
     * Removes all elements in the range of {@code startIndex}, inclusive, to {@code endIndex}, exclusive.
     *
     * @param startIndex
     *         the start index
     * @param endIndex
     *         the end index
     */
    public void removeInRange(int startIndex, int endIndex) {

        if (startIndex > endIndex)
            throw new IllegalArgumentException("The start index is bigger than the end index");

        for (int index = startIndex; index < endIndex; index++)
            this.remove(startIndex); // removes startIndex to compensate for shifting
    }

    /**
     * Performs the {@link Function Function} for the element belonging to the specified index and replaces the old
     * element with the
     * result of the {@link Function Function}.
     *
     * @param index the index of the element for which the {@link Function Function} is to be performed
     * @param function
     *         the function
     */
    public void perform(int index, Function<JsonElement, JsonValue> function) {

        JsonElement oldElement = this.fields.get(index);
        JsonElement newElement = new JsonElement(function.apply(oldElement));

        this.fields.add(index, newElement);
    }

    /**
     * Returns the number of elements in this {@code JsonArray}.  If this list contains more than {@code
     * Integer.MAX_VALUE} elements, returns {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this {@code JsonArray}
     */
    public int size() {

        return this.fields.size();
    }

    /**
     * Returns {@code true} if this {@code JsonArray} contains no elements.
     *
     * @return {@code true} if this {@code JsonArray} contains no elements
     */
    public boolean isEmpty() {

        return this.fields.isEmpty();
    }

    /**
     * Removes all of the elements from this {@code JsonArray} (optional operation). The list will be empty after this
     * call returns.
     *
     * @throws UnsupportedOperationException
     *         if the {@code clear} operation is not supported by this {@code JsonArray}
     */
    public void clear() {

        this.fields.clear();
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<JsonElement> iterator() {

        return this.fields.iterator();
    }

    /**
     * Returns a sequential {@link Stream Stream} of the elements of this {@code JsonArray}.
     *
     * @return the {@link Stream Stream}
     */
    public Stream<JsonElement> stream() {

        return this.fields.stream();
    }

    /**
     * Checks whether or not this {@code JsonArray} is equal to the provided {@link Object Object}.
     *
     * @param o
     *         the {@link Object Object} that is to be compared
     *
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof JsonArray))
            return false;

        JsonArray that = (JsonArray) o;

        return this.fields.equals(that.fields);
    }

    /**
     * Generates a hash code that is unique to every object, that is not equal to one another, as defined by {@link
     * #equals(Object)}, but is the same for every {@code JsonArray} that is equal.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {

        return this.fields.hashCode();
    }


}
