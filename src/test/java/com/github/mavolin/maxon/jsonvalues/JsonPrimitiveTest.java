package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonPrimitiveTest {


    @Test
    void isNullTest() {

        JsonPrimitive jsonNull = new JsonPrimitive((String) null);
        JsonPrimitive jsonBoolean = new JsonPrimitive(true);
        JsonPrimitive jsonCharacter = new JsonPrimitive('a');
        JsonPrimitive jsonNumber = new JsonPrimitive(12345);
        JsonPrimitive jsonString = new JsonPrimitive("String");
        
        assertTrue(jsonNull.isNull());
        assertFalse(jsonBoolean.isNull());
        assertFalse(jsonCharacter.isNull());
        assertFalse(jsonNumber.isNull());
        assertFalse(jsonString.isNull());
    }

    @Test
    void isBooleanTest() {

        JsonPrimitive jsonNull = new JsonPrimitive((String) null);
        JsonPrimitive jsonBoolean = new JsonPrimitive(true);
        JsonPrimitive jsonCharacter = new JsonPrimitive('a');
        JsonPrimitive jsonNumber = new JsonPrimitive(12345);
        JsonPrimitive jsonString = new JsonPrimitive("String");

        assertFalse(jsonNull.isBoolean());
        assertTrue(jsonBoolean.isBoolean());
        assertFalse(jsonCharacter.isBoolean());
        assertFalse(jsonNumber.isBoolean());
        assertFalse(jsonString.isBoolean());
    }

    @Test
    void isCharacterTest() {

        JsonPrimitive jsonNull = new JsonPrimitive((String) null);
        JsonPrimitive jsonBoolean = new JsonPrimitive(true);
        JsonPrimitive jsonCharacter = new JsonPrimitive('a');
        JsonPrimitive jsonNumber = new JsonPrimitive(12345);
        JsonPrimitive jsonString = new JsonPrimitive("String");

        assertFalse(jsonNull.isCharacter());
        assertFalse(jsonBoolean.isCharacter());
        assertTrue(jsonCharacter.isCharacter());
        assertFalse(jsonNumber.isCharacter());
        assertFalse(jsonString.isCharacter());
    }

    @Test
    void isNumberTest() {

        JsonPrimitive jsonNull = new JsonPrimitive((String) null);
        JsonPrimitive jsonBoolean = new JsonPrimitive(true);
        JsonPrimitive jsonCharacter = new JsonPrimitive('a');
        JsonPrimitive jsonNumber = new JsonPrimitive(12345);
        JsonPrimitive jsonString = new JsonPrimitive("String");

        assertFalse(jsonNull.isNumber());
        assertFalse(jsonBoolean.isNumber());
        assertFalse(jsonCharacter.isNumber());
        assertTrue(jsonNumber.isNumber());
        assertFalse(jsonString.isNumber());
    }

    @Test
    void isStringTest() {

        JsonPrimitive jsonNull = new JsonPrimitive((String) null);
        JsonPrimitive jsonBoolean = new JsonPrimitive(true);
        JsonPrimitive jsonCharacter = new JsonPrimitive('a');
        JsonPrimitive jsonNumber = new JsonPrimitive(12345);
        JsonPrimitive jsonString = new JsonPrimitive("String");

        assertFalse(jsonNull.isString());
        assertFalse(jsonBoolean.isString());
        assertFalse(jsonCharacter.isString());
        assertFalse(jsonNumber.isString());
        assertTrue(jsonString.isString());
    }

    @Test
    void getAsBooleanTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(true);

        assertTrue(jsonPrimitive.getAsBoolean());
    }


    @Test
    void getAsBooleanThrowsIllegealTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Boolean!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsBoolean);
    }

    @Test
    void getAsCharacterTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive('m');

        assertEquals('m', jsonPrimitive.getAsCharacter());
    }

    @Test
    void getAsCharacterThrowsIllegalTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Character!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsCharacter);
    }

    @Test
    void getAsByteTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive((byte) 123);

        assertEquals((byte) 123, jsonPrimitive.getAsByte());
    }

    @Test
    void getAsByteThrowsIllegalTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsByte);
    }

    @Test
    void getAsShortTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive((short) 1234);

        assertEquals((short) 1234, jsonPrimitive.getAsShort());
    }

    @Test
    void getAsShortThrowsIllegalTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsShort);
    }

    @Test
    void getAsIntegerTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(12345);

        assertEquals(1234, jsonPrimitive.getAsInteger());
    }

    @Test
    void getAsIntegerThrowsIllegalTypeRequestException() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsInteger);
    }

    @Test
    void getAsLongTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(123456L);

        assertEquals(123456L, jsonPrimitive.getAsLong());
    }

    @Test
    void getAsLongThrowsIllegalTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestException.class, jsonPrimitive::getAsLong);
    }


}