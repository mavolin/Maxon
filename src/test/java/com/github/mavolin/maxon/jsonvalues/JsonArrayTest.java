package com.github.mavolin.maxon.jsonvalues;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class JsonArrayTest {


    @Test
    void setBooleanTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray.add(false);

        assertEquals(false, jsonArray.set(0, true).getAsBoolean()); // previous value of jsonArray[0] was false, set
        // to true
        assertEquals(true, jsonArray.getAsBoolean(0)); // check if jsonArray[0] is true now

        jsonArray.set(0, (Boolean) null); // set to null
        assertNull(jsonArray.getAsBoolean(0)); // check if jsonArray[0] is null

        assertEquals(1, jsonArray.size()); // check if the array kept its size
    }

    @Test
    void setCharacterTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray.add('a');

        assertEquals('a', jsonArray.set(0, 'b').getAsCharacter());
        assertEquals('b', jsonArray.getAsCharacter(0));

        jsonArray.set(0, (Character) null);
        assertNull(jsonArray.getAsCharacter(0));

        assertEquals(1, jsonArray.size());
    }

    @Test
    void setNumberTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray.add(1);

        assertEquals(1, jsonArray.set(0, 2).getAsInteger());
        assertEquals(2, jsonArray.getAsInteger(0));

        jsonArray.set(0, (Integer) null);
        assertNull(jsonArray.getAsInteger(0));

        assertEquals(1, jsonArray.size());
    }

    @Test
    void setStringTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray.add("Hello World");

        assertEquals("Hello World", jsonArray.set(0, "Hello Milky Way").getAsString());
        assertEquals("Hello Milky Way", jsonArray.getAsString(0));

        jsonArray.set(0, (String) null);
        assertNull(jsonArray.getAsString(0));

        assertEquals(1, jsonArray.size());
    }

    @Test
    void setJsonValueTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray.add(new JsonPrimitive("String"));

        assertEquals(new JsonPrimitive("String").getAsString(), jsonArray.set(0, new JsonObject()).getAsString());
        assertEquals(new JsonObject(), jsonArray.getAsJsonObject(0));

        jsonArray.set(0, (JsonValue) null);
        assertNull(jsonArray.getAsJsonObject(0));

        assertEquals(1, jsonArray.size());
    }

    @Test
    void removeInRangeTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(0)
                .add(1)
                .add(2)
                .add(3)
                .add(4);

        jsonArray.removeInRange(1, 4);

        assertEquals(2, jsonArray.size());
        assertEquals(0, jsonArray.getAsInteger(0));
        assertEquals(4, jsonArray.getAsInteger(1));
    }

    @Test
    void iteratorHasNextTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(0)
                .add(1)
                .add(2);
        Iterator<JsonElement> iterator = jsonArray.iterator();

        for (int i = 0; i < 3; i++) {

            assertTrue(iterator.hasNext());
            iterator.next();
        }

        assertFalse(iterator.hasNext());
    }


}