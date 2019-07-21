package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    void getAsBooleanThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Boolean!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsBoolean);
    }

    @Test
    void getAsCharacterTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive('m');

        assertEquals('m', jsonPrimitive.getAsCharacter());
    }

    @Test
    void getAsCharacterThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Character!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsCharacter);
    }

    @Test
    void getAsByteTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive((byte) 123);

        assertEquals((byte) 123, jsonPrimitive.getAsByte());
    }

    @Test
    void getAsByteThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsByte);
    }

    @Test
    void getAsShortTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive((short) 1234);

        assertEquals((short) 1234, jsonPrimitive.getAsShort());
    }

    @Test
    void getAsShortThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsShort);
    }

    @Test
    void getAsIntegerTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(12345);

        assertEquals(1234, jsonPrimitive.getAsInteger());
    }

    @Test
    void getAsIntegerThrowsIllegalTypeRequestException() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsInteger);
    }

    @Test
    void getAsLongTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(123456L);

        assertEquals(123456L, jsonPrimitive.getAsLong());
    }

    @Test
    void getAsLongThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsLong);
    }

    @Test
    void getAsFloatTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(123.45f);

        assertEquals(123.45f, jsonPrimitive.getAsFloat());
    }

    @Test
    void getAsFloatThrowsIllegalTypeRequestException() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsFloat);
    }

    @Test
    void getAsBigIntegerTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(1234567890L);

        assertEquals(BigInteger.valueOf(123457890L), jsonPrimitive.getAsBigInteger());
    }

    @Test
    void getAsBigIntegerThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsBigInteger);
    }

    @Test
    void getAsDoubleTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(1234.56);

        assertEquals(1234.56, jsonPrimitive.getAsDouble());
    }

    @Test
    void getAsDoubleThrowsIllegalTypeRequestExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsDouble);
    }

    @Test
    void getAsBigDecimalTest() {

        JsonPrimitive jsonNumber = new JsonPrimitive(1234567.890d);

        assertEquals(BigDecimal.valueOf(1234567.890d), jsonNumber.getAsBigDecimal());
    }

    @Test
    void getAsBigDecimalThrowsIllegalTypeRequestedExceptionTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("This is a String, not a Number!");

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsBigDecimal);
    }

    @Test
    void getAsStringTest() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive("Look at me - I'm a truly beautiful String!");

        assertEquals("Look at me - I'm a truly beautiful String!", jsonPrimitive.getAsString());
    }

    @Test
    void getAsStringThrowsIllegalTypeRequestException() {

        JsonPrimitive jsonPrimitive = new JsonPrimitive(321);

        assertThrows(IllegalTypeRequestedException.class, jsonPrimitive::getAsString);
    }


}