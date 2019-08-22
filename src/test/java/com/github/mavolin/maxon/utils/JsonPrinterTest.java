package com.github.mavolin.maxon.utils;

import com.github.mavolin.maxon.PrintStyle;
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
    void printJsonArrayNoWhitespaceTest() {

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
    void printJsonArraySingleWhitespaceTest() {

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
    void printJsonArrayPrettyPrintedTest() {

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

        String testArrayStringNull = "[\n" +
                                     "    123,\n" +
                                     "    [\n" +
                                     "        {\n" +
                                     "            \"testKey1\": \"Hello World!\",\n" +
                                     "            \"AnotherKey\": 321,\n" +
                                     "            \"Null\": null\n" +
                                     "        },\n" +
                                     "        true\n" +
                                     "    ],\n" +
                                     "    false,\n" +
                                     "    null\n" +
                                     "]";
        String testArrayStringNoNull = "[\n" +
                                       "    123,\n" +
                                       "    [\n" +
                                       "        {\n" +
                                       "            \"testKey1\": \"Hello World!\",\n" +
                                       "            \"AnotherKey\": 321\n" +
                                       "        },\n" +
                                       "        true\n" +
                                       "    ],\n" +
                                       "    false\n" +
                                       "]";

        assertEquals(testArrayStringNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, false, PrintStyle.PRETTY_PRINTED));
        assertEquals(testArrayStringNoNull,
                     JsonPrinter.printJsonArray(testArray, ' ', 4, true, PrintStyle.PRETTY_PRINTED));
    }

    @Test
    void printJsonArrayPrettyPrintedWhitespaceCharTest() {

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

        String testArrayStringNull = "[\n" +
                                     "\t123,\n" +
                                     "\t[\n" +
                                     "\t\t{\n" +
                                     "\t\t\t\"testKey1\": \"Hello World!\",\n" +
                                     "\t\t\t\"AnotherKey\": 321,\n" +
                                     "\t\t\t\"Null\": null\n" +
                                     "\t\t},\n" +
                                     "\t\ttrue\n" +
                                     "\t],\n" +
                                     "\tfalse,\n" +
                                     "\tnull\n" +
                                     "]";
        String testArrayStringNoNull = "[\n" +
                                       "\t123,\n" +
                                       "\t[\n" +
                                       "\t\t{\n" +
                                       "\t\t\t\"testKey1\": \"Hello World!\",\n" +
                                       "\t\t\t\"AnotherKey\": 321\n" +
                                       "\t\t},\n" +
                                       "\t\ttrue\n" +
                                       "\t],\n" +
                                       "\tfalse\n" +
                                       "]";

        assertEquals(testArrayStringNull,
                     JsonPrinter.printJsonArray(testArray, '\t', 1, false, PrintStyle.PRETTY_PRINTED));
        assertEquals(testArrayStringNoNull,
                     JsonPrinter.printJsonArray(testArray, '\t', 1, true, PrintStyle.PRETTY_PRINTED));
    }

    @Test
    void printJsonObjectNoWhitespaceTest() {

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
    void printJsonObjectSingleWhitespaceTest() {

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

    @Test
    void printJsonObjectPrettyPrintedTest() {

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

        String testObjectStringNull = "{\n" +
                                      "    \"1\": \"Hello World!\",\n" +
                                      "    \"2\": 123,\n" +
                                      "    \"3\": [\n" +
                                      "        321,\n" +
                                      "        null,\n" +
                                      "        true\n" +
                                      "    ],\n" +
                                      "    \"4\": {\n" +
                                      "        \"4.1\": false,\n" +
                                      "        \"4.2\": \"m\",\n" +
                                      "        \"4.3\": 132\n" +
                                      "    },\n" +
                                      "    \"5\": null\n" +
                                      "}";
        String testObjectStringNoNull = "{\n" +
                                        "    \"1\": \"Hello World!\",\n" +
                                        "    \"2\": 123,\n" +
                                        "    \"3\": [\n" +
                                        "        321,\n" +
                                        "        true\n" +
                                        "    ],\n" +
                                        "    \"4\": {\n" +
                                        "        \"4.1\": false,\n" +
                                        "        \"4.2\": \"m\",\n" +
                                        "        \"4.3\": 132\n" +
                                        "    }\n" +
                                        "}";

        assertEquals(testObjectStringNull, JsonPrinter.printJsonObject(testObject, ' ', 4, false,
                                                                       PrintStyle.PRETTY_PRINTED));
        assertEquals(testObjectStringNoNull, JsonPrinter.printJsonObject(testObject, ' ', 4, true,
                                                                         PrintStyle.PRETTY_PRINTED));
    }

    @Test
    void printJsonObjectPrettyPrintedWhitespaceCharTest() {

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

        String testObjectStringNull = "{\n" +
                                      "\t\"1\": \"Hello World!\",\n" +
                                      "\t\"2\": 123,\n" +
                                      "\t\"3\": [\n" +
                                      "\t\t321,\n" +
                                      "\t\tnull,\n" +
                                      "\t\ttrue\n" +
                                      "\t],\n" +
                                      "\t\"4\": {\n" +
                                      "\t\t\"4.1\": false,\n" +
                                      "\t\t\"4.2\": \"m\",\n" +
                                      "\t\t\"4.3\": 132\n" +
                                      "\t},\n" +
                                      "\t\"5\": null\n" +
                                      "}";
        String testObjectStringNoNull = "{\n" +
                                        "\t\"1\": \"Hello World!\",\n" +
                                        "\t\"2\": 123,\n" +
                                        "\t\"3\": [\n" +
                                        "\t\t321,\n" +
                                        "\t\ttrue\n" +
                                        "\t],\n" +
                                        "\t\"4\": {\n" +
                                        "\t\t\"4.1\": false,\n" +
                                        "\t\t\"4.2\": \"m\",\n" +
                                        "\t\t\"4.3\": 132\n" +
                                        "\t}\n" +
                                        "}";

        assertEquals(testObjectStringNull, JsonPrinter.printJsonObject(testObject, '\t', 1, false,
                                                                       PrintStyle.PRETTY_PRINTED));
        assertEquals(testObjectStringNoNull, JsonPrinter.printJsonObject(testObject, '\t', 1, true,
                                                                         PrintStyle.PRETTY_PRINTED));
    }


}