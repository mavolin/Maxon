package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;
import com.github.mavolin.maxon.convert.AbortOnMissingField;
import com.github.mavolin.maxon.convert.DeserializationConstructor;
import com.github.mavolin.maxon.convert.Serialize;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UniversalObjectConverterTest {


    @Test
    void getAsJsonTest() {

        UniversalObjectConverter universalObjectConverter = new UniversalObjectConverter();

        assertEquals(new JsonObject()
                             .put("String", (String) null)
                             .put("i", 3), universalObjectConverter.getAsJson(new SerializeObject(), new Maxon()));
    }

    @Test
    void getFromJsonTest() {

        UniversalObjectConverter universalObjectConverter = new UniversalObjectConverter();

        StandardObject testObject = new StandardObject();
        testObject.str = "Hello World!";
        testObject.i = 9;

        JsonObject testObjectJson = new JsonObject();
        testObjectJson
                .put("str", "Hello World!")
                .put("i", 9);

        assertEquals(testObject,
                     universalObjectConverter.getFromJson(testObjectJson, StandardObject.class, new Maxon()));
    }

    @Test
    void getFromJsonAbortOnMissingFieldTest() {

        UniversalObjectConverter universalObjectConverter = new UniversalObjectConverter();

        AbortOnMissingFieldObject testObject = new AbortOnMissingFieldObject();
        testObject.i = 9;

        JsonObject missingStr = new JsonObject();
        missingStr
                .put("i", 9);

        JsonObject missingI = new JsonObject();
        missingI
                .put("str", "Hello World!");

        assertEquals(testObject,
                     universalObjectConverter.getFromJson(missingStr, AbortOnMissingFieldObject.class, new Maxon()));
        assertThrows(JsonParsingException.class, () ->
                universalObjectConverter.getFromJson(missingI, AbortOnMissingFieldObject.class, new Maxon()));
    }

    @Test
    void getFromJsonDeserializationConstructorTest() {

        UniversalObjectConverter universalObjectConverter = new UniversalObjectConverter();

        DeserializationConstructorObject testObject = new DeserializationConstructorObject("Hello World!");
        testObject.i = 9;

        JsonObject testObjectJson = new JsonObject();
        testObjectJson
                .put("str", "Hello World!")
                .put("i", 9);

        assertEquals(testObject,
                     universalObjectConverter.getFromJson(testObjectJson, DeserializationConstructorObject.class,
                                                          new Maxon()));
    }

    static class StandardObject {


        public String str;
        protected int i;

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            StandardObject that = (StandardObject) o;
            return i == that.i &&
                   str.equals(that.str);
        }


    }


    static class SerializeObject {


        @Serialize("String")
        public String str;
        @Serialize
        int i = 3;
        boolean bool = false;


    }


    @AbortOnMissingField(true)
    static class AbortOnMissingFieldObject {


        @AbortOnMissingField(false)
        public String str;
        protected int i;

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AbortOnMissingFieldObject that = (AbortOnMissingFieldObject) o;
            return i == that.i &&
                   Objects.equals(this.str, that.str);
        }


    }


    static class DeserializationConstructorObject {


        public String str;
        protected int i;

        @DeserializationConstructor("str")
        public DeserializationConstructorObject(String str) {

            this.str = str;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DeserializationConstructorObject that = (DeserializationConstructorObject) o;
            return i == that.i &&
                   str.equals(that.str);
        }


    }


}