package com.github.mavolin.maxon.jsonvalues;

import com.github.mavolin.maxon.exceptions.IllegalTypeRequestedException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    void getAsBooleanTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(true)
                .add("Not a Boolean");

        assertTrue(jsonArray.getAsBoolean(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsBoolean(1));
    }

    @Test
    void getAsCharacterTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add('a')
                .add("Not a Character");

        assertEquals('a', jsonArray.getAsCharacter(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsCharacter(1));
    }

    @Test
    void getAsByteTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(123)
                .add("Not a Byte");

        assertEquals((byte) 123, jsonArray.getAsByte(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsByte(1));
    }

    @Test
    void getAsShortTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(1234)
                .add("Not a Short");

        assertEquals((short) 1234, jsonArray.getAsShort(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsShort(1));
    }

    @Test
    void getAsIntegerTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(12345)
                .add("Not an Integer");

        assertEquals(12345, jsonArray.getAsInteger(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsInteger(1));
    }

    @Test
    void getAsLongTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(123456)
                .add("Not a Long");

        assertEquals(123456L, jsonArray.getAsLong(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsLong(1));
    }

    @Test
    void getAsBigIntegerTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(BigInteger.valueOf(1234567))
                .add("Not a BigInteger");

        assertEquals(BigInteger.valueOf(1234567), jsonArray.getAsBigInteger(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsBigInteger(1));
    }

    @Test
    void getAsFloatTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(1234.56)
                .add("Not a Float");

        assertEquals(1234.56f, jsonArray.getAsFloat(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsFloat(1));
    }

    @Test
    void getAsDoubleTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(12345.67)
                .add("Not a Double");

        assertEquals(12345.67, jsonArray.getAsDouble(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsDouble(1));
    }

    @Test
    void getAsBigDecimalTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(BigDecimal.valueOf(1234567))
                .add("Not a BigDecimal");

        assertEquals(BigDecimal.valueOf(1234567), jsonArray.getAsBigDecimal(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsBigDecimal(1));
    }

    @Test
    void getAsStringTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add("Hello World")
                .add(123);

        assertEquals("Hello World", jsonArray.getAsString(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsString(1));
    }

    @Test
    void getAsJsonArrayTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(new JsonArray())
                .add("Not a JsonArray");

        assertEquals(new JsonArray(), jsonArray.getAsJsonArray(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsJsonArray(1));
    }

    @Test
    void getAsJsonObjectTest() {

        JsonArray jsonArray = new JsonArray();

        jsonArray
                .add(new JsonObject())
                .add("Not a JsonObject");

        assertEquals(new JsonObject(), jsonArray.getAsJsonObject(0));
        assertThrows(IllegalTypeRequestedException.class, () ->
                jsonArray.getAsJsonObject(1));
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