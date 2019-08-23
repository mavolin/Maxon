package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class PrimitivesConverterTest {


    @Test
    void getAsJsonTest() {

        PrimitivesConverter primitivesConverter = new PrimitivesConverter();

        assertEquals(new JsonPrimitive(true), primitivesConverter.getAsJson(true));
        assertEquals(new JsonPrimitive('m'), primitivesConverter.getAsJson('m'));
        assertEquals(new JsonPrimitive(123), primitivesConverter.getAsJson((byte) 123));
        assertEquals(new JsonPrimitive(1234), primitivesConverter.getAsJson((short) 1234));
        assertEquals(new JsonPrimitive(12345), primitivesConverter.getAsJson(12345));
        assertEquals(new JsonPrimitive(123456), primitivesConverter.getAsJson(123456L));
        assertEquals(new JsonPrimitive(1234.56), primitivesConverter.getAsJson(1234.56f));
        assertEquals(new JsonPrimitive(12345.67), primitivesConverter.getAsJson(12345.67D));
        assertEquals(new JsonPrimitive(123456789), primitivesConverter.getAsJson(BigInteger.valueOf(123456789)));
        assertEquals(new JsonPrimitive(12345.6789), primitivesConverter.getAsJson(BigDecimal.valueOf(12345.6789)));
        assertEquals(new JsonPrimitive("Hello World!"), primitivesConverter.getAsJson("Hello World!"));
        assertThrows(JsonParsingException.class, () ->
                primitivesConverter.getAsJson(new JsonArray()));
    }

    @Test
    void getFromJsonTest() {

        PrimitivesConverter primitivesConverter = new PrimitivesConverter();

        JsonPrimitive trueBool = new JsonPrimitive(true);
        JsonPrimitive falseBool = new JsonPrimitive(false);
        JsonPrimitive character = new JsonPrimitive('m');
        JsonPrimitive num = new JsonPrimitive(123);
        JsonPrimitive string = new JsonPrimitive("Hello World!");

        assertTrue(primitivesConverter.getFromJson(trueBool, Boolean.class));
        assertFalse(primitivesConverter.getFromJson(falseBool, boolean.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(character, Boolean.class));

        assertEquals('m', primitivesConverter.getFromJson(character, Character.class));
        assertEquals('m', primitivesConverter.getFromJson(character, char.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(num, Character.class));

        assertEquals((byte) 123, primitivesConverter.getFromJson(num, Byte.class));
        assertEquals((byte) 123, primitivesConverter.getFromJson(num, byte.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Byte.class));

        assertEquals((short) 123, primitivesConverter.getFromJson(num, Short.class));
        assertEquals((short) 123, primitivesConverter.getFromJson(num, short.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Short.class));

        assertEquals(123, primitivesConverter.getFromJson(num, Integer.class));
        assertEquals(123, primitivesConverter.getFromJson(num, int.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Integer.class));

        assertEquals(123L, primitivesConverter.getFromJson(num, Long.class));
        assertEquals(123L, primitivesConverter.getFromJson(num, long.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Long.class));

        assertEquals(123f, primitivesConverter.getFromJson(num, Float.class));
        assertEquals(123f, primitivesConverter.getFromJson(num, float.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Float.class));

        assertEquals(123, primitivesConverter.getFromJson(num, Double.class));
        assertEquals(123, primitivesConverter.getFromJson(num, double.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, Double.class));

        assertEquals(BigInteger.valueOf(123), primitivesConverter.getFromJson(num, BigInteger.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, BigInteger.class));

        assertEquals(BigDecimal.valueOf(123), primitivesConverter.getFromJson(num, BigDecimal.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(string, BigInteger.class));

        assertEquals("Hello World!", primitivesConverter.getFromJson(string, String.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                primitivesConverter.getFromJson(trueBool, String.class));
    }


}