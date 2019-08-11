package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.InvalidKeyException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectTest {


    @Test
    void putThrowsNullPointerExceptionTest() {

        JsonObject jsonObject = new JsonObject();

        assertThrows(NullPointerException.class, () ->
                jsonObject.put(null, new JsonPrimitive(1)));
    }

    @Test
    void putOnceThrowsNullPointerExceptionTest() {

        JsonObject jsonObject = new JsonObject();

        assertThrows(NullPointerException.class, () ->
                jsonObject.putOnce(null, new JsonPrimitive(123)));
    }

    @Test
    void putOnceTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject.putOnce("test", 123);

        assertEquals(1, jsonObject.size());
        assertEquals(123, jsonObject.getAsInteger("test"));

        jsonObject.putOnce("test", 321);

        assertEquals(1, jsonObject.size());
        assertEquals(123, jsonObject.getAsInteger("test"));
    }

    @Test
    void getAsBooleanTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", true)
                .put("2", "This is not a Boolean");

        assertTrue(jsonObject.getAsBoolean("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsBoolean("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsBoolean("2"));
    }

    @Test
    void getAsCharacterTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", 'a')
                .put("2", "This is not a Character");

        assertEquals('a', jsonObject.getAsCharacter("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsCharacter("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsCharacter("2"));
    }

    @Test
    void getAsShortTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", (short) 123)
                .put("2", "This is not a Short");

        assertEquals((short) 123, jsonObject.getAsShort("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsShort("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsShort("2"));
    }

    @Test
    void getAsIntegerTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", 123)
                .put("2", "This is not a Integer");

        assertEquals((short) 123, jsonObject.getAsInteger("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsInteger("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsInteger("2"));
    }

    @Test
    void getAsLongTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", 123L)
                .put("2", "This is not a Long");

        assertEquals(123L, jsonObject.getAsLong("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsLong("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsLong("2"));
    }

    @Test
    void getAsBigIntegerTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", BigInteger.valueOf(123))
                .put("2", "This is not a BigInteger");

        assertEquals(BigInteger.valueOf(123), jsonObject.getAsBigInteger("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsBigInteger("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsBigInteger("2"));
    }

    @Test
    void getAsFloatTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", 123.45f)
                .put("2", "This is not a Float");

        assertEquals(123.45f, jsonObject.getAsFloat("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsFloat("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsFloat("2"));
    }

    @Test
    void getAsDoubleTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", 123.45f)
                .put("2", "This is not a Double");

        assertEquals(123.45f, jsonObject.getAsDouble("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsDouble("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsDouble("2"));
    }

    @Test
    void getAsBigDecimalTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", BigDecimal.valueOf(123.45))
                .put("2", "This is not a BigDecimal");

        assertEquals(BigDecimal.valueOf(123.45), jsonObject.getAsBigDecimal("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsBigDecimal("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsBigDecimal("2"));
    }

    @Test
    void getAsStringTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", "Hello World")
                .put("2", 123);

        assertEquals("Hello World", jsonObject.getAsString("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsString("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsString("2"));
    }

    @Test
    void getAsJsonArrayTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", new JsonArray())
                .put("2", "This is not a JsonArray");

        assertEquals(new JsonArray(), jsonObject.getAsJsonArray("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsJsonArray("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsJsonArray("2"));
    }

    @Test
    void getAsJsonObjectTest() {

        JsonObject jsonObject = new JsonObject();

        jsonObject
                .put("1", new JsonObject())
                .put("2", "This is not a JsonObject");

        assertEquals(new JsonObject(), jsonObject.getAsJsonObject("1"));
        assertThrows(InvalidKeyException.class, () ->
                jsonObject.getAsJsonObject("Some invalid key"));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonObject.getAsJsonObject("2"));
    }


}