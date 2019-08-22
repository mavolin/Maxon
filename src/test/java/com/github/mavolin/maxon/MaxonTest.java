package com.github.mavolin.maxon;

import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxonTest {


    @Test
    void getAsJsonObjectTest() {

        Maxon maxon = new Maxon();

        assertEquals("null", maxon.getAsJson((Object) null));
        assertEquals("123456.789", maxon.getAsJson(123456.789));
        assertThrows(UnsupportedOperationException.class, () ->
                maxon.getAsJson(maxon));
    }

    @Test
    void getAsJsonJsonValueTest() {

        Maxon maxon = new Maxon();
        JsonValue fakeValue = new JsonValue(){};

        assertEquals("123456.789", maxon.getAsJson(new JsonPrimitive(123456.789)));
        assertEquals("[123, true, \"Hello World!\"]", maxon.getAsJson(new JsonArray()
                                                                              .add(123)
                                                                              .add((String) null)
                                                                              .add("Hello World!")));
        assertEquals("{\"1\": 321, \"2\": \"Hello World!\", \"3\": false}", maxon.getAsJson(new JsonObject()
                                                                                                    .put("1", 321)
                                                                                                    .put("2",
                                                                                                         "Hello World!")
                                                                                                    .put("3", false)));
        assertThrows(IllegalArgumentException.class, () ->
                maxon.getAsJson(fakeValue));
    }

    @Test
    void getFromJsonTest() {

        Maxon maxon = new Maxon();

        assertEquals(new JsonPrimitive(123), maxon.getFromJson("123", JsonPrimitive.class));
        assertNull(maxon.getFromJson("null", String.class));
        assertEquals(123, maxon.getFromJson("123", int.class));
        assertThrows(UnsupportedOperationException.class, () ->
                maxon.getFromJson("123", Maxon.class));
    }


}