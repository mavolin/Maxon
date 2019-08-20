package com.github.mavolin.maxon.utils;

import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonPrinterTest {


    @Test
    void printJsonPrimitiveTest() {

        JsonPrimitive aNull = JsonPrimitive.NULL;
        JsonPrimitive bool = new JsonPrimitive(true);
        JsonPrimitive character = new JsonPrimitive('m');
        JsonPrimitive backslash = new JsonPrimitive('\\');
        JsonPrimitive quote = new JsonPrimitive('\"');
        JsonPrimitive backspace = new JsonPrimitive('\b');
        JsonPrimitive formFeed = new JsonPrimitive('\f');
        JsonPrimitive newline = new JsonPrimitive('\n');
        JsonPrimitive carriageReturn = new JsonPrimitive('\r');
        JsonPrimitive tabulator = new JsonPrimitive('\t');
        JsonPrimitive number = new JsonPrimitive(1234.56789);
        JsonPrimitive string = new JsonPrimitive("Hello World \\\"\b\f\n\r\t");

        assertEquals("null", JsonPrinter.printJsonPrimitive(aNull));
        assertEquals("true", JsonPrinter.printJsonPrimitive(bool));
        assertEquals("\"m\"", JsonPrinter.printJsonPrimitive(character));
        assertEquals("\"\\\\\"", JsonPrinter.printJsonPrimitive(backslash));
        assertEquals("\"\\\"\"", JsonPrinter.printJsonPrimitive(quote));
        assertEquals("\"\\b\"", JsonPrinter.printJsonPrimitive(backspace));
        assertEquals("\"\\f\"", JsonPrinter.printJsonPrimitive(formFeed));
        assertEquals("\"\\n\"", JsonPrinter.printJsonPrimitive(newline));
        assertEquals("\"\\r\"", JsonPrinter.printJsonPrimitive(carriageReturn));
        assertEquals("\"\\t\"", JsonPrinter.printJsonPrimitive(tabulator));
        assertEquals("1234.56789", JsonPrinter.printJsonPrimitive(number));
        assertEquals("\"Hello World \\\\\\\"\\b\\f\\n\\r\\t\"", JsonPrinter.printJsonPrimitive(string));
    }

    @Test
    void printJsonArrayNoWhitespace() {

        JsonArray testArray = new JsonArray();
        testArray
                .add(123)
                .add(new JsonArray()
                             .add(new JsonObject()
                                          .put("testKey1", "Hello World!")
                                          .put("AnotherKey", 321)
                                          .put("Null", (String) null))
                             .add(true))
                .add(false)
                .add((String) null);

        String testArrayStringNull =
                "[123,[{\"testKey1\":\"Hello World!\",\"AnotherKey\":321,\"Null\":null},true],false," +
                "null]";
        String testArrayStringNoNull = "[123,[{\"testKey1\":\"Hello World!\",\"AnotherKey\":321},true],false]";

        assertEquals(testArrayStringNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, false, PrintStyle.NO_WHITESPACE));
        assertEquals(testArrayStringNoNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, true, PrintStyle.NO_WHITESPACE));
    }

    @Test
    void printJsonArraySingleWhitespace() {

        JsonArray testArray = new JsonArray();
        testArray
                .add(123)
                .add(new JsonArray()
                             .add(new JsonObject()
                                          .put("testKey1", "Hello World!")
                                          .put("AnotherKey", 321)
                                          .put("Null", (String) null))
                             .add(true))
                .add(false)
                .add((String) null);

        String testArrayStringNull
                = "[123, [{\"testKey1\": \"Hello World!\", \"AnotherKey\": 321, \"Null\": null}, true], false, null]";
        String testArrayStringNoNull = "[123, [{\"testKey1\": \"Hello World!\", \"AnotherKey\": 321}, true], false]";

        assertEquals(testArrayStringNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, false, PrintStyle.SINGLE_WHITESPACE));
        assertEquals(testArrayStringNoNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, true, PrintStyle.SINGLE_WHITESPACE));
    }

    @Test
    void printJsonObjectNoWhitespace() {

        JsonObject testObject = new JsonObject();
        testObject
                .put("1", "Hello World!")
                .put("2", 123)
                .put("3", new JsonArray()
                        .add(321)
                        .add((String) null)
                        .add(true))
                .put("4", new JsonObject()
                        .put("4.1", false)
                        .put("4.2", 'm')
                        .put("4.3", 132))
                .put("5", (String) null);

        String testObjectStringNull
                = "{\"1\":\"Hello World!\",\"2\":123,\"3\":[321,null,true],\"4\":{\"4.1\":false,\"4.2\":\"m\",\"4" +
                  ".3\":132},\"5\":null}";
        String testObjectStringNoNull
                = "{\"1\":\"Hello World!\",\"2\":123,\"3\":[321,true],\"4\":{\"4.1\":false,\"4.2\":\"m\",\"4.3\":132}}";

        assertEquals(testObjectStringNull, JsonPrinter.printJsonObject(testObject, ' ', 4, false,
                                                                       PrintStyle.NO_WHITESPACE));
        assertEquals(testObjectStringNoNull, JsonPrinter.printJsonObject(testObject, ' ', 4, true,
                                                                         PrintStyle.NO_WHITESPACE));
    }

    @Test
    void printJsonObjectSingleWhitespace() {

        JsonObject testObject = new JsonObject();
        testObject
                .put("1", "Hello World!")
                .put("2", 123)
                .put("3", new JsonArray()
                        .add(321)
                        .add((String) null)
                        .add(true))
                .put("4", new JsonObject()
                        .put("4.1", false)
                        .put("4.2", 'm')
                        .put("4.3", 132))
                .put("5", (String) null);

        String testObjectStringNull
                = "{\"1\": \"Hello World!\", \"2\": 123, \"3\": [321, null, true], \"4\": {\"4.1\": false, \"4.2\": " +
                  "\"m\", \"4.3\": 132}, \"5\": null}";
        String testObjectStringNoNull
                = "{\"1\": \"Hello World!\", \"2\": 123, \"3\": [321, true], \"4\": {\"4.1\": false, \"4.2\": \"m\", " +
                  "\"4.3\": 132}}";

        assertEquals(testObjectStringNull, JsonPrinter.printJsonObject(testObject, ' ', 4, false,
                                                                       PrintStyle.SINGLE_WHITESPACE));
        assertEquals(testObjectStringNoNull, JsonPrinter.printJsonObject(testObject, ' ', 4, true,
                                                                         PrintStyle.SINGLE_WHITESPACE));
    }


}