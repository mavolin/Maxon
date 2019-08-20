package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.*;

import static org.junit.jupiter.api.Assertions.*;

class AtomicNumberConverterTest {


    @Test
    void getAsJsonTest() {

        AtomicNumberConverter aNC = new AtomicNumberConverter();

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
    }

    @Test
    void getFromJsonTest() {

        AtomicNumberConverter aNC = new AtomicNumberConverter();

        JsonPrimitive atomicBoolean = new JsonPrimitive(true);
        JsonPrimitive atomicInteger = new JsonPrimitive(123);
        JsonArray atomicIntegerArray = new JsonArray();
        atomicIntegerArray
                .add(1)
                .add(2)
                .add(3)
                .add(4);
        JsonPrimitive atomicLong = new JsonPrimitive(123456);
        JsonArray atomicLongArray = new JsonArray();
        atomicLongArray
                .add(12)
                .add(34)
                .add(56)
                .add(78);

        AtomicBoolean convertedAtomicBoolean = aNC.getFromJson(atomicBoolean, AtomicBoolean.class);
        AtomicInteger convertedAtomicInteger = aNC.getFromJson(atomicInteger, AtomicInteger.class);
        AtomicIntegerArray convertedAtomicIntegerArray = aNC.getFromJson(atomicIntegerArray, AtomicIntegerArray.class);
        AtomicLong convertedAtomicLong = aNC.getFromJson(atomicLong, AtomicLong.class);
        AtomicLongArray convertedAtomicLongArray = aNC.getFromJson(atomicLongArray, AtomicLongArray.class);

        assertTrue(convertedAtomicBoolean.get());
        assertEquals(123, convertedAtomicInteger.get());
        assertEquals(new AtomicIntegerArray(new int[]{1, 2, 3, 4}).toString(), convertedAtomicIntegerArray.toString());
        assertEquals(123456, convertedAtomicLong.get());
        assertEquals(new AtomicLongArray(new long[]{12, 34, 56, 78}).toString(), convertedAtomicLongArray.toString());
    }


}