package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

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

        JsonPrimitive bool = new JsonPrimitive(true);
        JsonElement boolElement = new JsonElement(bool);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Boolean) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Boolean");
        JsonElement stringElement = new JsonElement(string);

        assertTrue(boolElement.getAsBoolean());
        assertNull(nullElement.getAsBoolean());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsBoolean);
    }

    @Test
    void getAsCharacterTest() {

        JsonPrimitive character = new JsonPrimitive('a');
        JsonElement characterElement = new JsonElement(character);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Character) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);
        
        JsonPrimitive string = new JsonPrimitive("Not a Character");
        JsonElement stringElement = new JsonElement(string);

        assertEquals('a', characterElement.getAsCharacter());
        assertNull(nullElement.getAsCharacter());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsCharacter);
    }

    @Test
    void getAsByteTest() {

        JsonPrimitive bytePrimitive = new JsonPrimitive(123);
        JsonElement byteElement = new JsonElement(bytePrimitive);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Byte) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Byte");
        JsonElement stringElement = new JsonElement(string);

        assertEquals((byte) 123, byteElement.getAsByte());
        assertNull(nullElement.getAsByte());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsByte);
    }

    @Test
    void getAsShortTest() {

        JsonPrimitive shortPrimitive = new JsonPrimitive(1234);
        JsonElement shortElement = new JsonElement(shortPrimitive);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Short) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Short");
        JsonElement stringElement = new JsonElement(string);

        assertEquals((short) 1234, shortElement.getAsShort());
        assertNull(nullElement.getAsShort());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsShort);
    }

    @Test
    void getAsIntegerTest() {

        JsonPrimitive integer = new JsonPrimitive(12345);
        JsonElement integerElement = new JsonElement(integer);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Integer) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Integer");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(12345, integerElement.getAsInteger());
        assertNull(nullElement.getAsInteger());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsInteger);
    }

    @Test
    void getAsLongTest() {

        JsonPrimitive longPrimitive = new JsonPrimitive(123456);
        JsonElement longElement = new JsonElement(longPrimitive);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Long) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Long");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(123456L, longElement.getAsLong());
        assertNull(nullElement.getAsLong());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsLong);
    }

    @Test
    void getAsBigIntegerTest() {

        JsonPrimitive bigInteger = new JsonPrimitive(new BigInteger("123456"));
        JsonElement bigIntegerElement = new JsonElement(bigInteger);

        JsonPrimitive nullPrimitive = new JsonPrimitive((BigInteger) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a BigInteger");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(new BigInteger("123456"), bigIntegerElement.getAsBigInteger());
        assertNull(nullElement.getAsBigInteger());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsBigInteger);
    }

    @Test
    void getAsFloatTest() {

        JsonPrimitive floatPrimitive = new JsonPrimitive(1234.56);
        JsonElement floatElement = new JsonElement(floatPrimitive);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Float) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Float");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(1234.56f, floatElement.getAsFloat());
        assertNull(nullElement.getAsFloat());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsFloat);
    }

    @Test
    void getAsDoubleTest() {

        JsonPrimitive doublePrimitive = new JsonPrimitive(12345.67);
        JsonElement doubleElement = new JsonElement(doublePrimitive);

        JsonPrimitive nullPrimitive = new JsonPrimitive((Double) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a Double");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(12345.67, doubleElement.getAsDouble());
        assertNull(nullElement.getAsDouble());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsDouble);
    }

    @Test
    void getAsBigDecimalTest() {

        JsonPrimitive bigDecimal = new JsonPrimitive(new BigDecimal("12345.6789"));
        JsonElement bigDecimalElement = new JsonElement(bigDecimal);

        JsonPrimitive nullPrimitive = new JsonPrimitive((BigDecimal) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive string = new JsonPrimitive("Not a BigDecimal");
        JsonElement stringElement = new JsonElement(string);

        assertEquals(new BigDecimal("12345.6789"), bigDecimalElement.getAsBigDecimal());
        assertNull(nullElement.getAsBigDecimal());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsBigDecimal);
    }

    @Test
    void getAsStringTest() {

        JsonPrimitive string = new JsonPrimitive("This is a test String.");
        JsonElement stringElement = new JsonElement(string);

        JsonPrimitive nullPrimitive = new JsonPrimitive((String) null);
        JsonElement nullElement = new JsonElement(nullPrimitive);

        JsonPrimitive number = new JsonPrimitive(123);
        JsonElement numberElement = new JsonElement(number);

        assertEquals("This is a test String.", stringElement.getAsString());
        assertNull(nullElement.getAsString());
        assertThrows(IllegalTypeRequestedException.class, numberElement::getAsString);
    }

    @Test
    void getAsJsonArrayTest() {

        JsonArray jsonArray = new JsonArray();
        jsonArray
                .add(1)
                .add(2)
                .add("Hello World");
        JsonElement jsonArrayElement = new JsonElement(jsonArray);

        JsonPrimitive string = new JsonPrimitive("Not a JsonObject");
        JsonElement stringElement = new JsonElement(string);

        JsonElement nullElement = new JsonElement(null);

        assertEquals(jsonArray, jsonArrayElement.getAsJsonArray());
        assertNull(nullElement.getAsJsonArray());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsJsonArray);
    }

    @Test
    void getAsJsonObjectTest() {

        JsonObject jsonObject = new JsonObject();
        jsonObject
                .put("First", 1)
                .put("second", true)
                .put("Last", 'v');
        JsonElement jsonElement = new JsonElement(jsonObject);

        JsonPrimitive string = new JsonPrimitive("Not a JsonObject");
        JsonElement stringElement = new JsonElement(string);

        JsonElement nullElement = new JsonElement(null);

        assertEquals(jsonObject, jsonElement.getAsJsonObject());
        assertNull(nullElement.getAsJsonObject());
        assertThrows(IllegalTypeRequestedException.class, stringElement::getAsJsonObject);
    }


}