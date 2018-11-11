package com.example.htmlparser.service;

import com.example.htmlparser.model.TextProcessingInput;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextProcessingUtilTest {

    @Test
    public void 숫자인지확인() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        assertTrue(utilService.isNumber("0".getBytes()[0]));
        assertTrue(utilService.isNumber("9".getBytes()[0]));
        assertFalse(utilService.isNumber("A".getBytes()[0]));
        assertFalse(utilService.isNumber("!".getBytes()[0]));
    }

    @Test
    public void 영문인지확인() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        assertTrue(utilService.isAlphabets("A".getBytes()[0]));
        assertTrue(utilService.isAlphabets("a".getBytes()[0]));
        assertTrue(utilService.isAlphabets("Z".getBytes()[0]));
        assertTrue(utilService.isAlphabets("z".getBytes()[0]));
        assertFalse(utilService.isAlphabets("1".getBytes()[0]));
        assertFalse(utilService.isAlphabets("!".getBytes()[0]));
    }

    @Test
    public void html내용가져오기() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        TextProcessingInput input = new TextProcessingInput();
        input.setType(TextProcessingInput.Type.HTML);
        input.setUnit(1);
        input.setUrl("http://www.naver.com");
        String result = utilService.parseString(input);
        assertTrue(result.length() > 0);
    }

    @Test
    public void text내용가져오기() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        TextProcessingInput input = new TextProcessingInput();
        input.setType(TextProcessingInput.Type.TEXT);
        input.setUnit(1);
        input.setUrl("http://www.naver.com");
        String result = utilService.parseString(input);
        assertTrue(result.length() > 0);
    }

    @Test
    public void text내용가져오기Exception발생() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        TextProcessingInput input = new TextProcessingInput();
        input.setType(TextProcessingInput.Type.TEXT);
        input.setUnit(1);
        input.setUrl("test-location");
        String result = utilService.parseString(input);
        assertTrue(("Exception").equals(result));
    }

    @Test
    public void merge() {
        TextProcessingUtil utilService = new TextProcessingUtil();
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
        List<String> alphabets = Arrays.asList("A", "a", "B", "b", "c");
        String result = utilService.merge(numbers, alphabets);
        assertTrue(("A1a2B3b4c5").equals(result));
    }

}