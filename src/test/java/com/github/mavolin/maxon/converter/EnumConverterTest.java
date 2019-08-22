package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.convert.SerializedName;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.PrintStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumConverterTest {


    @Test
    void getAsJsonTest() {

        EnumConverter enumConverter = new EnumConverter();

        assertThrows(JsonParsingException.class, () ->
                enumConverter.getAsJson(new Object()));
        assertEquals(new JsonPrimitive("PRETTY_PRINTED"), enumConverter.getAsJson(PrintStyle.PRETTY_PRINTED));
        assertEquals(new JsonPrimitive("new Name"), enumConverter.getAsJson(SerializedEnum.TEST));
    }

    @Test
    void getFromJsonTest() {

        EnumConverter enumConverter = new EnumConverter();

        assertThrows(JsonParsingException.class, () ->
                enumConverter.getFromJson(new JsonArray(), int[].class));
        assertThrows(JsonParsingException.class, () ->
                enumConverter.getFromJson(new JsonPrimitive(123), int.class));
        assertEquals(PrintStyle.PRETTY_PRINTED,
                     enumConverter.getFromJson(new JsonPrimitive("PRETTY_PRINTED"), PrintStyle.class));
        assertEquals(SerializedEnum.TEST,
                     enumConverter.getFromJson(new JsonPrimitive("new Name"), SerializedEnum.class));
        assertThrows(JsonParsingException.class, () ->
                     enumConverter.getFromJson(new JsonPrimitive("Not an enum"), SerializedEnum.class));
    }

    enum SerializedEnum {
        @SerializedName("new Name")
        TEST
    }


}