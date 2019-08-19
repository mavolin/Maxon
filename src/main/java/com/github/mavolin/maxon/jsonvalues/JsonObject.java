package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.InvalidKeyException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

/**
 * A {@code JsonObject} is a map-like representation of a JSON object. Every object consists of a key and value, where
 * the key represents the name of the variable stored, and the value its data. A value can be of all JSON types, and
 * just as in Java JSON objects can store inner objects.
 */
public class JsonObject implements JsonValue {


    private static final String NULL_KEY_ERR_MSG = "The provided key is null";
    private static final String ILLEGAL_TYPE_REQUEST_ERR_TMPL
            = "The element mapped to \"%s\" does not resemble a %s";
    /**
     * Stores the content of the {@code JsonObject}. The key represents the name of the variable and the value its
     * data/value.
     */
    private Map<String, JsonElement> fields = new LinkedHashMap<>();


    /**
     * Instantiates an empty {@code JsonObject}.
     */
    public JsonObject() {

    }


    /**
     * Associates the passed {@link Boolean Boolean} with the specified key and puts it in the map. If there is already
     * an element associated with the provided key, it will be overwritten.
     *
     * @param key
     *         the key
     * @param bool
     *         the {@link Boolean Boolean} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject put(String key, Boolean bool) {

        if (key == null) {
            throw new NullPointerException(NULL_KEY_ERR_MSG);
        }

        this.put(key, new JsonPrimitive(bool));

        return this;
    }

    /**
     * Associates the passed {@link Character Character} with the specified key and puts it in the map. If there is
     * already an element associated with the provided key, it will be overwritten.
     *
     * @param key
     *         the key
     * @param character
     *         the {@link Character Character} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject put(String key, Character character) {

        this.put(key, new JsonPrimitive(character));

        return this;
    }

    /**
     * Associates the passed {@link Number Number} with the specified key and puts it in the map. If there is already an
     * element associated with the provided key, it will be overwritten.
     *
     * @param key
     *         the key
     * @param num
     *         the {@link Number Number} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject put(String key, Number num) {

        this.put(key, new JsonPrimitive(num));

        return this;
    }

    /**
     * Associates the passed {@link String String} with the specified key and puts it in the map. If there is already an
     * element associated with the provided key, it will be overwritten.
     *
     * @param key
     *         the key
     * @param string
     *         the {@link String String} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject put(String key, String string) {

        this.put(key, new JsonPrimitive(string));

        return this;
    }

    /**
     * Associates the passed {@link JsonValue JsonValue} with the specified key and puts it in the map. If there is
     * already an element associated with the provided key, it will be overwritten.
     *
     * @param key
     *         the key
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject put(String key, JsonValue jsonValue) {

        if (key == null) {
            throw new NullPointerException(NULL_KEY_ERR_MSG);
        }

        this.fields.put(key,
                        Objects.requireNonNullElse(new JsonElement(jsonValue), new JsonElement(JsonPrimitive.NULL)));

        return this;
    }

    /**
     * Puts the passed {@link Boolean Boolean} into the map and associates it with the passed key, if there is not
     * already an element with the passed key in the list. If this is the case, nothing will be changed.
     *
     * @param key
     *         the key
     * @param bool
     *         the {@link Boolean Boolean} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject putOnce(String key, Boolean bool) {

        this.putOnce(key, new JsonPrimitive(bool));

        return this;
    }

    /**
     * Puts the passed {@link Character Character} into the map and associates it with the passed key, if there is not
     * already an element with the passed key in the list. If this is the case, nothing will be changed.
     *
     * @param key
     *         the key
     * @param character
     *         the {@link Character Character} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject putOnce(String key, Character character) {

        this.putOnce(key, new JsonPrimitive(character));

        return this;
    }

    /**
     * Puts the passed {@link Number Number} into the map and associates it with the passed key, if there is not already
     * an element with the passed key in the list. If this is the case, nothing will be changed.
     *
     * @param key
     *         the key
     * @param num
     *         the {@link Number Number} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject putOnce(String key, Number num) {

        this.putOnce(key, new JsonPrimitive(num));

        return this;
    }

    /**
     * Puts the passed {@link String String} into the map and associates it with the passed key, if there is not already
     * an element with the passed key in the list. If this is the case, nothing will be changed.
     *
     * @param key
     *         the key
     * @param string
     *         the {@link String String} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject putOnce(String key, String string) {

        this.putOnce(key, new JsonPrimitive(string));

        return this;
    }

    /**
     * Puts the passed {@link JsonValue JsonValue} into the map and associates it with the passed key, if there is not
     * already an element with the passed key in the list. If this is the case, nothing will be changed.
     *
     * @param key
     *         the key
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to be added
     *
     * @return itself
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     */
    public JsonObject putOnce(String key, JsonValue jsonValue) {

        if (key == null) {
            throw new NullPointerException(NULL_KEY_ERR_MSG);
        }

        if (!this.fields.containsKey(key)) {
            this.fields.put(key, Objects.requireNonNullElse(new JsonElement(jsonValue), new JsonElement(jsonValue)));
        }

        return this;
    }

    /**
     * Gets all elements stored in the passed {@code JsonObject} and puts them into this {@code JsonObject}.
     *
     * @param jsonObject
     *         the {@code JsonObject} which's elements are to be put in this {@code JsonObject}
     */
    public void putAll(JsonObject jsonObject) {

        this.fields.putAll(jsonObject.fields);
    }

    /**
     * Remove the the element associated with the specified key and returns the removed element as represented by a
     * {@link JsonElement JsonElement}. If there was no element associated with the passed key, {@code null} will be
     * returned.
     *
     * @param key
     *         the key
     *
     * @return the json element
     *
     * @throws NullPointerException
     *         if the passed key is {@code null}
     */
    public JsonElement remove(String key) {

        if (key == null) {
            throw new NullPointerException(NULL_KEY_ERR_MSG);
        }

        return this.fields.remove(key);
    }

    /**
     * Gets the {@link Boolean Boolean} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Boolean Boolean}
     *
     * @return the {@link Boolean Boolean} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Boolean Boolean}
     */
    public Boolean getAsBoolean(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isBoolean() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Boolean"));
        }

        return jsonElement.getAsBoolean();
    }

    /**
     * Gets the {@link Character Character} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Character Character}
     *
     * @return the {@link Character Character} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Character Character}
     */
    public Character getAsCharacter(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isCharacter() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Character"));
        }

        return jsonElement.getAsCharacter();
    }

    /**
     * Gets the {@link Short Short} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Short Short}
     *
     * @return the {@link Short Short} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Short Short}
     */
    public Short getAsShort(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Short"));
        }

        return jsonElement.getAsShort();
    }

    /**
     * Gets the {@link Integer Integer} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Integer Integer}
     *
     * @return the {@link Integer Integer} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Integer Integer}
     */
    public Integer getAsInteger(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Integer"));
        }

        return jsonElement.getAsInteger();
    }

    /**
     * Gets the {@link Long Long} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Long Long}
     *
     * @return the {@link Long Long} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Long Long}
     */
    public Long getAsLong(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Long"));
        }

        return jsonElement.getAsLong();
    }

    /**
     * Gets the {@link BigInteger BigInteger} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link BigInteger BigInteger}
     *
     * @return the {@link BigInteger BigInteger} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link BigInteger BigInteger}
     */
    public BigInteger getAsBigInteger(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "BigInteger"));
        }

        return jsonElement.getAsBigInteger();
    }

    /**
     * Gets the {@link Float Float} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Float Float}
     *
     * @return the {@link Float Float} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Float Float}
     */
    public Float getAsFloat(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Float"));
        }

        return jsonElement.getAsFloat();
    }

    /**
     * Gets the {@link Double Double} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Double Double}
     *
     * @return the {@link Double Double} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Double Double}
     */
    public Double getAsDouble(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "Double"));
        }

        return jsonElement.getAsDouble();
    }

    /**
     * Gets the {@link BigDecimal BigDecimal} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link BigDecimal BigDecimal}
     *
     * @return the {@link BigDecimal BigDecimal} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link BigDecimal BigDecimal}
     */
    public BigDecimal getAsBigDecimal(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isNumber() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "BigDecimal"));
        }

        return jsonElement.getAsBigDecimal();
    }

    /**
     * Gets the {@link Boolean Boolean} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link Boolean Boolean}
     *
     * @return the {@link Boolean Boolean} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link Boolean Boolean}
     */
    public String getAsString(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isString() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "String"));
        }

        return jsonElement.getAsString();
    }

    /**
     * Gets the {@link JsonArray JsonArray} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link JsonArray JsonArray}
     *
     * @return the {@link JsonArray JsonArray} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link JsonArray JsonArray}
     */
    public JsonArray getAsJsonArray(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isJsonArray() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "JsonArray"));
        }

        return jsonElement.getAsJsonArray();
    }

    /**
     * Gets the {@link JsonObject JsonObject} associated with the provided key.
     *
     * @param key
     *         the key belonging to the requested {@link JsonObject JsonObject}
     *
     * @return the {@link JsonObject JsonObject} associated with the specified key
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     * @throws IllegalTypeRequestedException
     *         if the element the key is mapped to is not an instance of {@link JsonObject JsonObject}
     */
    public JsonObject getAsJsonObject(String key) {

        JsonElement jsonElement = this.checkKeyGet(key);

        if (!jsonElement.isJsonObject() && !jsonElement.isNull()) {
            throw new IllegalTypeRequestedException(String.format(ILLEGAL_TYPE_REQUEST_ERR_TMPL, key, "JsonObject"));
        }

        return jsonElement.getAsJsonObject();
    }

    /**
     * Performs the {@link Function Function} for the element belonging to the specified key and replaces the old
     * element with the result of the {@link Function Function}
     *
     * @param key
     *         the key of the element on which the {@link Function Function} is to be performed
     * @param function
     *         the function
     *
     * @throws NullPointerException
     *         If the provided key is {@code null}
     * @throws InvalidKeyException
     *         if the specified key does not belong to a mapping
     */
    public void perform(String key, Function<JsonElement, JsonValue> function) {

        JsonElement oldElement = this.checkKeyGet(key);
        JsonElement newElement = new JsonElement(function.apply(oldElement));

        this.fields.put(key, newElement);
    }

    /**
     * Returns a {@link Set} view of the keys contained in this {@code JsonObject}. The set is backed by the {@code
     * JsonObject}, so changes to the {@code JsonObject} are reflected in the set, and vice-versa.  If the {@code
     * JsonObject} is modified while an iteration over the set is in progress (except through the iterator's own {@code
     * remove} operation), the results of the iteration are undefined.  The set supports element removal, which removes
     * the corresponding {@code JsonObject}ping from the {@code JsonObject}, via the {@code Iterator.remove}, {@code
     * Set.remove}, {@code removeAll}, {@code retainAll}, and {@code clear} operations.  It does not support the {@code
     * add} or {@code addAll} operations.
     *
     * @return a set view of the keys contained in this {@code JsonObject}
     */
    public Set<String> keySet() {

        return this.fields.keySet();
    }

    /**
     * Returns a {@link Collection} view of the values contained in this {@code JsonObject}. The collection is backed by
     * the {@code JsonObject}, so changes to the {@code JsonObject} are reflected in the collection, and vice-versa.  If
     * the {@code JsonObject} is modified while an iteration over the collection is in progress (except through the
     * iterator's own {@code remove} operation), the results of the iteration are undefined.  The collection supports
     * element removal, which removes the corresponding {@code JsonObject}ping from the {@code JsonObject}, via the
     * {@code Iterator.remove}, {@code Collection.remove}, {@code removeAll}, {@code retainAll} and {@code clear}
     * operations.  It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a collection view of the values contained in this {@code JsonObject}
     */
    public Collection<JsonElement> values() {

        return this.fields.values();
    }

    /**
     * Returns a {@link Set} view of the {@code JsonObject}pings contained in this {@code JsonObject}. The set is backed
     * by the {@code JsonObject}, so changes to the {@code JsonObject} are reflected in the set, and vice-versa.  If the
     * {@code JsonObject} is modified while an iteration over the set is in progress (except through the iterator's own
     * {@code remove} operation, or through the {@code setValue} operation on a {@code JsonObject} entry returned by the
     * iterator) the results of the iteration are undefined.  The set supports element removal, which removes the
     * corresponding {@code JsonObject}ping from the {@code JsonObject}, via the {@code Iterator.remove}, {@code
     * Set.remove}, {@code removeAll}, {@code retainAll} and {@code clear} operations.  It does not support the {@code
     * add} or {@code addAll} operations.
     *
     * @return a set view of the {@code JsonObject}pings contained in this {@code JsonObject}
     */
    public Set<Map.Entry<String, JsonElement>> entrySet() {

        return this.fields.entrySet();
    }

    /**
     * Checks if there is an element present, that is associated with the specified key.
     *
     * @param key
     *         the key
     *
     * @return {@code true} if there is an element associated with the passed key; {@code false} otherwise
     */
    public boolean has(String key) {

        return this.fields.containsKey(key);
    }

    /**
     * Checks if this {@code JsonObject} contains elements.
     *
     * @return {@code true} if this {@code JsonObject} contains at least one or more elements; {@code false} otherwise
     */
    public boolean isEmpty() {

        return this.fields.isEmpty();
    }

    /**
     * Removes all mappings in this {@code JsonObject}.
     */
    public void clear() {

        this.fields.clear();
    }

    /**
     * Returns the number of elements that are contained by the {@code JsonObject}
     *
     * @return the number of elements in the {@code JsonObject}
     */
    public int size() {

        return this.fields.size();
    }

    /**
     * Returns a {@link String String} representation of this {@code JsonObject}.
     *
     * @return a {@link String String} representation of this {@code JsonObject}
     */
    @Override
    public String toString() {

        return this.fields.toString();
    }

    /**
     * Checks, whether or not this {@code JsonObject} is equal to the provided {@code Object o}.
     *
     * @param o
     *         the {@link Object} that is to be compared with this {@code JsonObject}
     *
     * @return {@code true} if {@code Object o} and this {@code JsonObject} are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof JsonObject)) {
            return false;
        }

        JsonObject that = (JsonObject) o;

        return this.fields.equals(that.fields);
    }

    /**
     * Generates a hash code that is unique to every object, that is not equal to one another, as defined by {@link
     * #equals(Object)}, but is the same for every {@code JsonObject} that is equal.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {

        return this.fields.hashCode();
    }

    /**
     * Utility method used to check if the key is non {@code null} and is mapped. If not the corresponding exceptions
     * will be thrown.
     *
     * @param key
     *         the key of the element that is to be checked
     *
     * @return the {@link JsonElement JsonElement} associated with the provided key
     */
    private JsonElement checkKeyGet(String key) {

        if (key == null) {
            throw new NullPointerException(NULL_KEY_ERR_MSG);
        }

        JsonElement jsonElement = this.fields.get(key);

        if (jsonElement == null) {
            throw new InvalidKeyException("The provided key does not belong to a mapping");
        }

        return jsonElement;
    }


}
