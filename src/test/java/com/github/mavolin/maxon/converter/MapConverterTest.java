package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MapConverterTest {


    @Test
    void getAsJsonTest() {

        MapConverter mapConverter = new MapConverter();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        JsonObject jsonMap = new JsonObject();
        jsonMap
                .put("keyClass", "java.lang.String")
                .put("valueClass", "java.lang.Integer")
                .put("map", new JsonArray()
                        .add(new JsonObject()
                                     .put("key", "one")
                                     .put("value", 1))
                        .add(new JsonObject()
                                     .put("key", "two")
                                     .put("value", 2))
                        .add(new JsonObject()
                                     .put("key", "three")
                                     .put("value", 3)));

        assertEquals(jsonMap, mapConverter.getAsJson(map, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                mapConverter.getAsJson("Hello World!", new Maxon()));
    }

    @Test
    void getFromJson() {

        MapConverter mapConverter = new MapConverter();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        JsonObject jsonMap = new JsonObject();
        jsonMap
                .put("keyClass", "java.lang.String")
                .put("valueClass", "java.lang.Integer")
                .put("map", new JsonArray()
                        .add(new JsonObject()
                                     .put("key", "one")
                                     .put("value", 1))
                        .add(new JsonObject()
                                     .put("key", "two")
                                     .put("value", 2))
                        .add(new JsonObject()
                                     .put("key", "three")
                                     .put("value", 3)));

        assertEquals(map, mapConverter.getFromJson(jsonMap, HashMap.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                mapConverter.getFromJson(jsonMap, EnumMap.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                mapConverter.getFromJson(new JsonPrimitive("Hello World"), HashMap.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                mapConverter.getFromJson(jsonMap, String.class, new Maxon()));
    }


}