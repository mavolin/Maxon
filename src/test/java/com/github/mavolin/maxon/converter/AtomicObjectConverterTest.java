package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.*;

import static org.junit.jupiter.api.Assertions.*;

class AtomicObjectConverterTest {


    @Test
    void getAsJsonTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicInteger atomicInteger = new AtomicInteger(123);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4});
        AtomicLong atomicLong = new AtomicLong(123456);
        AtomicLongArray atomicLongArray = new AtomicLongArray(new long[]{12, 34, 56, 78});

        assertEquals(new JsonPrimitive(true), aNC.getAsJson(atomicBoolean));
        assertEquals(new JsonPrimitive(123), aNC.getAsJson(atomicInteger));
        assertEquals(new JsonArray()
                             .add(1)
                             .add(2)
                             .add(3)
                             .add(4), aNC.getAsJson(atomicIntegerArray));
        assertEquals(new JsonPrimitive(123456), aNC.getAsJson(atomicLong));
        assertEquals(new JsonArray()
                             .add(12)
                             .add(34)
                             .add(56)
                             .add(78), aNC.getAsJson(atomicLongArray));
        assertThrows(JsonParsingException.class, () ->
                aNC.getAsJson(new Object()));
    }

    @Test
    void getFromJsonTest() {

        assertThrows(JsonParsingException.class, () ->
                new AtomicObjectConverter().getFromJson(new JsonArray(), String.class));
    }


    @Test
    void getFromJsonAtomicBooleanTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();
        JsonPrimitive atomicBoolean = new JsonPrimitive(true);

        assertTrue(aNC.getFromJson(atomicBoolean, AtomicBoolean.class).get());
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonArray(), AtomicBoolean.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonPrimitive("Hello World"), AtomicBoolean.class));
    }

    @Test
    void getFromJsonAtomicIntegerTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();
        JsonPrimitive atomicInteger = new JsonPrimitive(123);

        assertEquals(123, aNC.getFromJson(atomicInteger, AtomicInteger.class).get());
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonArray(), AtomicInteger.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonPrimitive("Hello World"), AtomicInteger.class));
    }

    @Test
    void getFromJsonAtomicIntegerArrayTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();
        JsonArray atomicIntegerArrayJson = new JsonArray();
        atomicIntegerArrayJson
                .add(1)
                .add(2)
                .add(3)
                .add(4);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4});

        assertEquals(atomicIntegerArray.toString(),
                     aNC.getFromJson(atomicIntegerArrayJson, AtomicIntegerArray.class).toString());
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonObject(), AtomicIntegerArray.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonPrimitive("Hello World"), AtomicIntegerArray.class));
    }

    @Test
    void getFromJsonAtomicLongTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();
        JsonPrimitive atomicLong = new JsonPrimitive(123);

        assertEquals(123, aNC.getFromJson(atomicLong, AtomicLong.class).get());
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonArray(), AtomicLong.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonPrimitive("Hello World"), AtomicLong.class));
    }

    @Test
    void getFromJsonAtomicLongArrayTest() {

        AtomicObjectConverter aNC = new AtomicObjectConverter();
        JsonArray atomicLongArrayJson = new JsonArray();
        atomicLongArrayJson
                .add(1)
                .add(2)
                .add(3)
                .add(4);
        AtomicLongArray atomicLongArray = new AtomicLongArray(new long[]{1, 2, 3, 4});

        assertEquals(atomicLongArray.toString(),
                     aNC.getFromJson(atomicLongArrayJson, AtomicLongArray.class).toString());
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonObject(), AtomicLongArray.class));
        assertThrows(IllegalTypeRequestedException.class, () ->
                aNC.getFromJson(new JsonPrimitive("Hello World"), AtomicLongArray.class));
    }


}