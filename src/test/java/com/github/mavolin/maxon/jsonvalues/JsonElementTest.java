package com.github.mavolin.maxon.jsonvalues;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonElementTest {


    @Test
    void isNullTest() {

        JsonElement jsonElement = new JsonElement(null);

        assertTrue(jsonElement.isNull());
    }

    @Test
    void isBooleanTest() {

        JsonElement jsonElement = new JsonElement(new JsonPrimitive(false));

        assertTrue(jsonElement.isBoolean());
    }

    @Test
    void isCharacterTest() {

        JsonElement jsonElement = new JsonElement(new JsonPrimitive('a'));

        assertTrue(jsonElement.isCharacter());
    }

    @Test
    void isNumberTest() {

        JsonElement jsonElement = new JsonElement(new JsonPrimitive(132));

        assertTrue(jsonElement.isNumber());
    }

    @Test
    void isStringTest() {

        JsonElement jsonElement = new JsonElement(new JsonPrimitive("Hello World"));

        assertTrue(jsonElement.isString());
    }

    @Test
    void isJsonArrayTest() {

        JsonElement jsonElement = new JsonElement(new JsonArray());

        assertTrue(jsonElement.isJsonArray());
    }

    @Test
    void isJsonObjectTest() {

        JsonElement jsonElement = new JsonElement(new JsonObject());

        assertTrue(jsonElement.isJsonObject());
    }

    @Test
    void getAsBooleanTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(true);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertTrue(jsonElement.getAsBoolean());
    }

    @Test
    void getAsCharacterTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive('m');
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals('m', jsonElement.getAsCharacter());
    }

    @Test
    void getAsByteTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(123);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals((byte) 123, jsonElement.getAsByte());
    }

    @Test
    void getAsShortTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(1234);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals((short) 1234, jsonElement.getAsShort());
    }

    @Test
    void getAsIntegerTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(12345);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(12345, jsonElement.getAsInteger());
    }

    @Test
    void getAsLongTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(123456);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(123456L, jsonElement.getAsLong());
    }

    @Test
    void getAsBigIntegerTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(new BigInteger("123456"));
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(new BigInteger("123456"), jsonElement.getAsBigInteger());
    }

    @Test
    void getAsFloatTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(1234.56);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(1234.56f, jsonElement.getAsFloat());
    }

    @Test
    void getAsDoubleTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(12345.67);
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(12345.67, jsonElement.getAsDouble());
    }

    @Test
    void getAsBigDecimalTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(new BigDecimal("12345.6789"));
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals(new BigDecimal("12345.6789"), jsonElement.getAsBigDecimal());
    }

    @Test
    void getAsStringTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a test String.");
        JsonElement jsonElement = new JsonElement(jsonPrimitive);

        assertEquals("This is a test String.", jsonElement.getAsString());
    }

    @Test
    void getAsJsonArrayTest() {

        JsonArray jsonArray = new JsonArray();
        jsonArray
                .add(1)
                .add(2)
                .add("Hello World");
        JsonElement jsonElement = new JsonElement(jsonArray);

        assertEquals(jsonArray, jsonElement.getAsJsonArray());
    }

    @Test
    void getAsJsonObjectTest() {

        JsonObject jsonObject = new JsonObject();
        jsonObject
                .put("First", 1)
                .put("second", true)
                .put("Last", 'v');
        JsonElement jsonElement = new JsonElement(jsonObject);

        assertEquals(jsonObject, jsonElement.getAsJsonObject());
    }


}