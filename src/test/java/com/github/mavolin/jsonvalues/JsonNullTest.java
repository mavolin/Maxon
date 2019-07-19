package com.github.mavolin.jsonvalues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JsonNullTest {


    @Test
    void getTest() {

        JsonNull jsonNull = new JsonNull();

        assertNull(jsonNull.get());
    }

    @Test
    void getRawTest() {

        JsonNull jsonNull = new JsonNull();

        assertEquals("null", jsonNull.getRaw());
    }

    @Test
    void getPrettyPrintedTest() {

        JsonNull jsonNull = new JsonNull();

        assertEquals("null", jsonNull.getPrettyPrinted());
    }


}