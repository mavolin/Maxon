package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.Serialize;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversalObjectConverterTest {


    @Test
    void getAsJsonTest() {

        UniversalObjectConverter universalObjectConverter = new UniversalObjectConverter();

        assertEquals(new JsonObject()
                             .put("String", (String) null)
                             .put("i", 3), universalObjectConverter.getAsJson(new TestObject1(), new Maxon()));
    }

    class TestObject1 {


        @Serialize("String")
        public String str;
        @Serialize
        int i = 3;
        private boolean bool = false;


    }


}