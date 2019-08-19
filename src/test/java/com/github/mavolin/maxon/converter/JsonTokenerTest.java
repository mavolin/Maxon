package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.JsonParsingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTokenerTest {


    @Test
    void skipCommentAndWhitespaceTest() {

        JsonTokener jsonTokener = new JsonTokener(" \t\r\n \t// this a comment\n"
                                                          + " /5");

        jsonTokener.skipCommentAndWhitespace();

        assertEquals(24, jsonTokener.getIndex()); // we are at 6 so that next() would return e
        assertEquals('/', jsonTokener.next());
    }

    @Test
    void skipWhitespaceTest() {

        JsonTokener jsonTokener = new JsonTokener(" \t\r\n");

        jsonTokener.skipWhitespace();

        assertEquals(3, jsonTokener.getIndex());
    }

    @Test
    void hasNextTest() {

        JsonTokener jsonTokener = new JsonTokener(" ");

        assertTrue(jsonTokener.hasNext());

        jsonTokener.next();
        assertFalse(jsonTokener.hasNext());
    }

    @Test
    void nextTest() {

        JsonTokener jsonTokener = new JsonTokener("12");

        assertEquals('1', jsonTokener.next());
        assertEquals('2', jsonTokener.next());
    }

    @Test
    void nextIntTest() {

        JsonTokener jsonTokener = new JsonTokener("123");

        assertEquals("123", jsonTokener.next(3));
        assertThrows(JsonParsingException.class, () ->
                jsonTokener.next(1));
    }

    @Test
    void checkAndNextTest() {

        JsonTokener jsonTokener = new JsonTokener("12");

        assertEquals('1', jsonTokener.next());
        assertEquals('2', jsonTokener.next());
        assertThrows(JsonParsingException.class, jsonTokener::checkAndNext);
    }

    @Test
    void nextNoIncrementTest() {

        JsonTokener jsonTokener = new JsonTokener("1");

        assertEquals('1', jsonTokener.nextNoIncrement());
        assertEquals(-1, jsonTokener.getIndex());
    }

    @Test
    void checkAndNextNoIncrementTest() {

        JsonTokener jsonTokener = new JsonTokener("");

        assertThrows(JsonParsingException.class, jsonTokener::checkAndNextNoIncrement);
    }

    @Test
    void nextBooleanTest() {

        JsonTokener trueTokener = new JsonTokener("true");
        JsonTokener falseTokener = new JsonTokener("false");
        JsonTokener misspelledFalseTokener = new JsonTokener("falst");
        JsonTokener misspelledTrueTokener = new JsonTokener("truk");
        JsonTokener notABooleanTokener = new JsonTokener("antiBool");

        assertTrue(trueTokener.nextBoolean());
        assertFalse(falseTokener.nextBoolean());
        assertThrows(JsonParsingException.class, misspelledFalseTokener::nextBoolean);
        assertThrows(JsonParsingException.class, misspelledTrueTokener::nextBoolean);
        assertThrows(JsonParsingException.class, notABooleanTokener::nextBoolean);
    }

    @Test
    void nextCharacterTest() {

        JsonTokener aTokener = new JsonTokener("\"a\"");
        JsonTokener emptyStringTokener = new JsonTokener("\"\"");
        JsonTokener missingFirstQuote = new JsonTokener("a\"");
        JsonTokener missingLastQuote = new JsonTokener("\"a");

        assertEquals('a', aTokener.nextCharacter());
        assertThrows(JsonParsingException.class, emptyStringTokener::nextCharacter);
        assertThrows(JsonParsingException.class, missingFirstQuote::nextCharacter);
        assertThrows(JsonParsingException.class, missingLastQuote::nextCharacter);
    }

    @Test
    void nextCharacterIllegalCharTest() {

        JsonTokener newlineTokener = new JsonTokener("\n");
        JsonTokener carriageReturnTokener = new JsonTokener("\r");

        assertThrows(JsonParsingException.class, newlineTokener::nextCharacter);
        assertThrows(JsonParsingException.class, carriageReturnTokener::nextCharacter);
    }

    @Test
    void nextCharacterControlCharTest() {

        JsonTokener quoteTokener = new JsonTokener("\"\\\"\"");
        JsonTokener backslashTokener = new JsonTokener("\"\\\\\"");
        JsonTokener forwardSlashTokener = new JsonTokener("\"\\/\"");
        JsonTokener backspaceTokener = new JsonTokener("\"\\b\"");
        JsonTokener formFeedTokener = new JsonTokener("\"\\f\"");
        JsonTokener newlineTokener = new JsonTokener("\"\\n\"");
        JsonTokener carriageReturnTokener = new JsonTokener("\"\\r\"");
        JsonTokener tabTokener = new JsonTokener("\"\\t\"");
        JsonTokener unicodeTokener = new JsonTokener("\"\\u23FB\"");
        JsonTokener illegalControlCharTokener = new JsonTokener("\"\\m\"");

        assertEquals('"', quoteTokener.nextCharacter());
        assertEquals('\\', backslashTokener.nextCharacter());
        assertEquals('/', forwardSlashTokener.nextCharacter());
        assertEquals('\b', backspaceTokener.nextCharacter());
        assertEquals('\f', formFeedTokener.nextCharacter());
        assertEquals('\n', newlineTokener.nextCharacter());
        assertEquals('\r', carriageReturnTokener.nextCharacter());
        assertEquals('\t', tabTokener.nextCharacter());
        assertEquals('\u23fb', unicodeTokener.nextCharacter());
        assertThrows(JsonParsingException.class, illegalControlCharTokener::nextCharacter);
    }

    @Test
    void nextNumberTest() {

        new JsonTokener("-")
    }


}