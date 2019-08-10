package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A {@code JsonArray} is a list-like representation of an JSON array. In contrast to arrays found in Java, a JSON
 * array, is not limited to a list of objects of a specific type. This means, that the same JSON array may hold numbers
 * and strings.
 */
public class JsonArray implements JsonValue, Iterable<JsonElement> {


    /**
     * Holds the fields of the {@code JsonArray}.
     */
    private List<JsonValue> fields = new ArrayList<>();


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

        this.fields.add(new JsonPrimitive(bool));

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

        this.fields.add(new JsonPrimitive(character));

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

        this.fields.add(new JsonPrimitive(num));

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

        this.fields.add(new JsonPrimitive(string));

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

        this.fields.add(jsonValue);

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

        this.fields.add(index, new JsonPrimitive(bool));

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

        this.fields.add(index, new JsonPrimitive(character));

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

        this.fields.add(index, new JsonPrimitive(num));

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

        this.fields.add(index, new JsonPrimitive(string));

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

        this.fields.add(index, jsonValue);

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

        JsonValue val = this.fields.set(index, new JsonPrimitive(bool));

        return new JsonElement(val);
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

        JsonValue val = this.fields.set(index, new JsonPrimitive(character));

        return new JsonElement(val);
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

        JsonValue val = this.fields.set(index, new JsonPrimitive(num));

        return new JsonElement(val);
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

        JsonValue val = this.fields.set(index, new JsonPrimitive(string));

        return new JsonElement(val);
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

        JsonValue val = this.fields.set(index, jsonValue);

        return new JsonElement(val);
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Boolean");

        return ((JsonPrimitive) jsonValue).getAsBoolean();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Character");

        return ((JsonPrimitive) jsonValue).getAsCharacter();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Byte");

        return ((JsonPrimitive) jsonValue).getAsByte();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Short");

        return ((JsonPrimitive) jsonValue).getAsShort();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Integer");

        return ((JsonPrimitive) jsonValue).getAsInteger();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Long");

        return ((JsonPrimitive) jsonValue).getAsLong();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a " +
                                                            "BigInteger");

        return ((JsonPrimitive) jsonValue).getAsBigInteger();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Float");

        return ((JsonPrimitive) jsonValue).getAsFloat();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a Double");

        return ((JsonPrimitive) jsonValue).getAsDouble();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a " +
                                                            "BigDecimal");

        return ((JsonPrimitive) jsonValue).getAsBigDecimal();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonPrimitive))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a String");

        return ((JsonPrimitive) jsonValue).getAsString();
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonArray))
            throw new IllegalTypeRequestedException("The element at the specified index does not resemble a JsonArray");

        return (JsonArray) jsonValue;
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

        JsonValue jsonValue = this.fields.get(index);

        if (!(jsonValue instanceof JsonObject))
            throw new IllegalTypeRequestedException(
                    "The element at the specified index does not resemble a JsonObject");

        return (JsonObject) jsonValue;
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
    public JsonValue remove(int index) {

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

        return new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {

                return JsonArray.this.fields.size() < this.currentIndex;
            }

            @Override
            public JsonElement next() {

                if (JsonArray.this.fields.size() >= this.currentIndex)
                    throw new NoSuchElementException();

                JsonValue currentValue = JsonArray.this.fields.get(this.currentIndex++);

                return new JsonElement(currentValue);
            }

            @Override
            public void remove() {

                JsonArray.this.fields.remove(this.currentIndex);
            }
        };
    }

    /**
     * Returns a sequential {@link Stream Stream} of the elements of this {@code JsonArray}.
     *
     * @return the {@link Stream Stream}
     */
    public Stream<JsonElement> stream() {

        return StreamSupport.stream(this.spliterator(), false);
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
