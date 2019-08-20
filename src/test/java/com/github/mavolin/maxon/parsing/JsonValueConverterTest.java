package com.github.mavolin.maxon.parsing;

import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonValueConverterTest {


    @Test
    void getFromJsonTest() {

        JsonValueConverter jsonValueConverter = new JsonValueConverter();

        String jsonArrayJSON = "[ // This is a comment\n" +
                "1.35 // and another comment\n" +
                ", // and yet another one\n" +
                "true // here we go again\n" +
                ", // Comment\n" +
                "\"Last one\"]";
        String jsonObjectJSON = "{ // Your ad could be here ;)\n" +
                "\"TheKey01\" // whitespace and comment\n" +
                ": // and more whitespace\n" +
                "123 // comment incoming\n" +
                ", // Look, its a comma\n" +
                "\"AnotherKey\":true // nearly done\n" +
                "}";

        JsonArray jsonArrayJava = new JsonArray();
        jsonArrayJava
                .add(1.35)
                .add(true)
                .add("Last one");

        JsonObject jsonObjectJava = new JsonObject();
        jsonObjectJava
                .put("TheKey01", 123)
                .put("AnotherKey", true);

        JsonPrimitive bool = (JsonPrimitive) jsonValueConverter.getFromJson(" true ");
        JsonPrimitive number = (JsonPrimitive) jsonValueConverter.getFromJson("123.4567e+2");
        JsonPrimitive string = (JsonPrimitive) jsonValueConverter.getFromJson("\"Hello World!\"");
        JsonArray jsonArrayConverted = (JsonArray) jsonValueConverter.getFromJson(jsonArrayJSON);
        JsonObject jsonObjectConverted = (JsonObject) jsonValueConverter.getFromJson(jsonObjectJSON);

        assertTrue(bool.getAsBoolean());
        assertEquals(12345.67, number.getAsDouble());
        assertEquals("Hello World!", string.getAsString());
        assertEquals(jsonArrayJava, jsonArrayConverted);
        assertEquals(jsonObjectJava, jsonObjectConverted);
    }


}