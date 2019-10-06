package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ListConverterTest {


    @Test
    void getAsJsonTest() {

        ListConverter listConverter = new ListConverter();

        ArrayList<String> list = new ArrayList<>();
        list.add("Hello World!");
        list.add("Hello Galaxy!");
        list.add("Hello Universe!");

        JsonObject arrayAsJson = new JsonObject();
        arrayAsJson
                .put("itemClass", "java.lang.String")
                .put("array", new JsonArray()
                        .add("Hello World!")
                        .add("Hello Galaxy!")
                        .add("Hello Universe!"));

        assertEquals(arrayAsJson, listConverter.getAsJson(list, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                listConverter.getAsJson("Hello World!", new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                listConverter.getAsJson("", new Maxon()));
    }

    @Test
    void getFromJson() {

        ListConverter listConverter = new ListConverter();

        ArrayList<String> list = new ArrayList<>();
        list.add("Hello World!");
        list.add("Hello Galaxy!");
        list.add("Hello Universe!");

        JsonObject arrayAsJson = new JsonObject();
        arrayAsJson
                .put("itemClass", "java.lang.String")
                .put("array", new JsonArray()
                        .add("Hello World!")
                        .add("Hello Galaxy!")
                        .add("Hello Universe!"));

        assertEquals(list, listConverter.getFromJson(arrayAsJson, ArrayList.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                listConverter.getFromJson(new JsonPrimitive("Hello World"), HashMap.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                listConverter.getFromJson(arrayAsJson, String.class, new Maxon()));
    }


}