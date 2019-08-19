package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.JsonParsingException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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

        assertEquals(-1, jsonTokener.getIndex());

        assertEquals('1', jsonTokener.next());
        assertEquals(0, jsonTokener.getIndex());

        assertEquals('2', jsonTokener.next());
        assertEquals(1, jsonTokener.getIndex());
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

        JsonTokener negativeNumTokener = new JsonTokener("-1");
        JsonTokener nonNegativeNumTokener = new JsonTokener("1");
        JsonTokener doubleDigitTokener = new JsonTokener("12");
        JsonTokener decimalTokener = new JsonTokener("1.2");
        JsonTokener doubleDecimalTokener = new JsonTokener("1.23");
        JsonTokener smallExponentTokener = new JsonTokener("1e2");
        JsonTokener bigExponentTokener = new JsonTokener("1E2");
        JsonTokener positiveExponentTokener = new JsonTokener("1e+2");
        JsonTokener negativeExponentTokener = new JsonTokener("1e-2");
        JsonTokener doubleExponentTokener = new JsonTokener("1e23");

        assertEquals(BigDecimal.valueOf(-1), negativeNumTokener.nextNumber());
        assertEquals(1, negativeExponentTokener.getIndex()); // check that cursor is on the one, to prepare for
        // next() call
        assertEquals(BigDecimal.valueOf(1), nonNegativeNumTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(12), doubleDigitTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(1.2), decimalTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(1.23), doubleDecimalTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(100), smallExponentTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(100), bigExponentTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(100), positiveExponentTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(0.01), negativeExponentTokener.nextNumber());
        assertEquals(BigDecimal.valueOf(1e23), doubleDecimalTokener.nextNumber());
    }

    @Test
    void nextNumberIllegalCharTest() {

        JsonTokener illegalFirstDigit = new JsonTokener("m");
        JsonTokener illegalSecondDigit = new JsonTokener("1m");
        JsonTokener missingFirstDigit = new JsonTokener(".12");
        JsonTokener illegalFirstDecimal = new JsonTokener("1.m");
        JsonTokener illegalSecondDecimal = new JsonTokener("1.2m");
        JsonTokener illegalFirstExponent = new JsonTokener("1em");
        JsonTokener illegalSecondExponent = new JsonTokener("1e2m");

        assertThrows(JsonParsingException.class, illegalFirstDigit::nextNumber);
        assertThrows(JsonParsingException.class, illegalSecondDigit::nextNumber);
        assertThrows(JsonParsingException.class, missingFirstDigit::nextNumber);
        assertThrows(JsonParsingException.class, illegalFirstDecimal::nextNumber);
        assertThrows(JsonParsingException.class, illegalSecondDecimal::nextNumber);
        assertThrows(JsonParsingException.class, illegalFirstExponent::nextNumber);
        assertThrows(JsonParsingException.class, illegalSecondExponent::nextNumber);
    }

    @Test
    void nextStringTest() {

        JsonTokener helloWorldTokener = new JsonTokener("Hello World!");
        JsonTokener newlineTokener = new JsonTokener("\n");
        JsonTokener carriageReturnTokener = new JsonTokener("\r");
        JsonTokener controlCharacter = new JsonTokener("\\\", \\\\, \\/, \\b, \\f, \\n, \\r, \\t, \\u23fb");
        JsonTokener illegalControlCharacter = new JsonTokener("\\m");

        assertEquals("Hello World!", helloWorldTokener.nextString());
        assertThrows(JsonParsingException.class, newlineTokener::nextString);
        assertThrows(JsonParsingException.class, carriageReturnTokener::nextString);
        assertEquals("\", \\, /, \b, \f, \n, \r, \t, \u23fb", controlCharacter.nextString());
        assertThrows(JsonParsingException.class, illegalControlCharacter::nextString);
    }

    @Test
    void backTest() {

        JsonTokener jsonTokener = new JsonTokener("a");

        jsonTokener.next();
        assertEquals(0, jsonTokener.getIndex());

        jsonTokener.back();
        assertEquals(-1, jsonTokener.getIndex());

        assertThrows(IndexOutOfBoundsException.class, jsonTokener::back);
    }

    @Test
    void getIndexTest() {

        JsonTokener jsonTokener = new JsonTokener("abc");

        assertEquals(-1, jsonTokener.getIndex());

        jsonTokener.next();
        assertEquals(0, jsonTokener.getIndex());

        jsonTokener.next();
        assertEquals(1, jsonTokener.getIndex());
    }


}