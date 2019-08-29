package com.github.mavolin.maxon;

import com.github.mavolin.maxon.convert.Serialize;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MaxonTest {


    @Test
    void getAsJsonObjectTest() {

        Maxon maxon = new Maxon();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        String jsonMap
                = "{\"keyClass\": \"java.lang.String\", \"valueClass\": \"java.lang.Integer\", \"map\": [{\"key\":" +
                  "\"one\", \"value\": 1}, {\"key\": \"two\", \"value\": 2}, {\"key\": \"three\", \"value\": 3}]}";

        assertEquals("null", maxon.getAsJson((Object) null));
        assertEquals("123456.789", maxon.getAsJson(123456.789));
        assertEquals("\"PRETTY_PRINTED\"", maxon.getAsJson(PrintStyle.PRETTY_PRINTED));
        assertEquals(jsonMap, maxon.getAsJson(map));
        assertEquals("{\"str\": \"Hello World!\", \"i\": 3}", maxon.getAsJson(new TestObject1()));
    }

    @Test
    void getAsJsonJsonValueTest() {

        Maxon maxon = new Maxon();

        JsonValue fakeValue = new JsonValue() {

        };

        assertEquals("123456.789", maxon.getAsJson(new JsonPrimitive(123456.789)));
        assertEquals("[123, true, \"Hello World!\"]", maxon.getAsJson(new JsonArray()
                                                                              .add(123)
                                                                              .add(true)
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

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        String jsonMap
                = "{\"keyClass\": \"java.lang.String\", \"valueClass\": \"java.lang.Integer\", \"map\": [{\"key\":" +
                  "\"one\", \"value\": 1}, {\"key\": \"two\", \"value\": 2}, {\"key\": \"three\", \"value\": 3}]}";

        assertNull(maxon.getFromJson("null", Object.class));
        assertEquals(new JsonPrimitive(123), maxon.getFromJson("123", JsonPrimitive.class));
        assertEquals(123, maxon.getFromJson("123", int.class));
        assertEquals(PrintStyle.PRETTY_PRINTED, maxon.getFromJson("\"PRETTY_PRINTED\"", PrintStyle.class));
        assertEquals(map, maxon.getFromJson(jsonMap, HashMap.class));
        assertEquals(new TestObject1(),
                     maxon.getFromJson("{ \"str\": \"Hello World!\", \"i\": 3 }", TestObject1.class));
    }


    static class TestObject1 {


        public String str = "Hello World!";
        private int i = 3;

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TestObject1 that = (TestObject1) o;
            return i == that.i && str.equals(that.str);
        }


    }


}