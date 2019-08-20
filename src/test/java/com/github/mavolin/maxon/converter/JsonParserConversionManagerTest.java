package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.convert.JsonParser;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserConversionManagerTest {

    private final JsonParser exampleParser = new JsonParser<String>() {

        @Override
        public JsonValue getAsJson(String source) {

            return null;
        }

        @Override
        public String getFromJson(JsonValue source) {

            return null;
        }
    };

    @Test
    void registerParserThrowsNullPointerExceptionTest() {

        JsonParserConversionManager jsonParserConversionManager = new JsonParserConversionManager();

        assertThrows(NullPointerException.class, () ->
                jsonParserConversionManager.registerParser(null, String.class));
        assertThrows(NullPointerException.class, () ->
                jsonParserConversionManager.registerParser(JsonParserConversionManagerTest.this.exampleParser, null));
    }

    @Test
    void getAsJsonThrowsJsonParsingException() {

        JsonParserConversionManager jsonParserConversionManager = new JsonParserConversionManager();

        assertThrows(JsonParsingException.class, () ->
                jsonParserConversionManager.getAsJson(""));
    }

    @Test
    void getFromJsonThrowsJsonParsingException() {

        JsonParserConversionManager jsonParserConversionManager = new JsonParserConversionManager();

        assertThrows(JsonParsingException.class, () ->
                jsonParserConversionManager.getFromJson(new JsonPrimitive(""), String.class));
    }


}