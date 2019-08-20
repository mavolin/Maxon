package com.github.mavolin.maxon.utils;

import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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


}