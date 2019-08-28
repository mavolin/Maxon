package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.convert.SerializedName;
import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.PrintStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UniversalEnumConverterTest {


    @Test
    void getAsJsonTest() {

        UniversalEnumConverter universalEnumConverter = new UniversalEnumConverter();

        assertThrows(JsonParsingException.class, () ->
                universalEnumConverter.getAsJson(new Object()));
        assertEquals(new JsonPrimitive("PRETTY_PRINTED"), universalEnumConverter.getAsJson(PrintStyle.PRETTY_PRINTED));
        assertEquals(new JsonPrimitive("new Name"), universalEnumConverter.getAsJson(SerializedEnum.TEST));
    }

    @Test
    void getFromJsonTest() {

        UniversalEnumConverter universalEnumConverter = new UniversalEnumConverter();

        assertThrows(JsonParsingException.class, () ->
                universalEnumConverter.getFromJson(new JsonArray(), int[].class));
        assertThrows(JsonParsingException.class, () ->
                universalEnumConverter.getFromJson(new JsonPrimitive(123), int.class));
        assertEquals(PrintStyle.PRETTY_PRINTED,
                     universalEnumConverter.getFromJson(new JsonPrimitive("PRETTY_PRINTED"), PrintStyle.class));
        assertEquals(SerializedEnum.TEST,
                     universalEnumConverter.getFromJson(new JsonPrimitive("new Name"), SerializedEnum.class));
        assertThrows(JsonParsingException.class, () ->
                     universalEnumConverter.getFromJson(new JsonPrimitive("Not an enum"), SerializedEnum.class));
    }

    enum SerializedEnum {
        @SerializedName("new Name")
        TEST
    }


}