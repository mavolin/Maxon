package com.github.mavolin.maxon.defaultconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTokenerTest {


    @Test
    void skipCommentAndWhitespaceTest() {

        JsonTokener jsonTokener = new JsonTokener(" \t\r\n \t\nend");

        jsonTokener.skipCommentAndWhitespace();

        assertEquals(7, jsonTokener.getIndex());
    }


}