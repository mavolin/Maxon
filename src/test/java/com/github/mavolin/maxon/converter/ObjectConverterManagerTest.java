package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.convert.ObjectConverter;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectConverterManagerTest {

    private final ObjectConverter exampleParser = new ObjectConverter<String>() {

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

        ObjectConverterManager objectConverterManager = new ObjectConverterManager();

        assertThrows(NullPointerException.class, () ->
                objectConverterManager.registerParser(null, String.class));
        assertThrows(NullPointerException.class, () ->
                objectConverterManager.registerParser(ObjectConverterManagerTest.this.exampleParser, null));
    }

    @Test
    void getAsJsonThrowsJsonParsingException() {

        ObjectConverterManager objectConverterManager = new ObjectConverterManager();

        assertThrows(JsonParsingException.class, () ->
                objectConverterManager.getAsJson(""));
    }

    @Test
    void getFromJsonThrowsJsonParsingException() {

        ObjectConverterManager objectConverterManager = new ObjectConverterManager();

        assertThrows(JsonParsingException.class, () ->
                objectConverterManager.getFromJson(new JsonPrimitive(""), String.class));
    }


}