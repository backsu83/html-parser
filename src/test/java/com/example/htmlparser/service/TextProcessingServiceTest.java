package com.example.htmlparser.service;

import com.example.htmlparser.model.TextProcessingInput;
import com.example.htmlparser.model.TextProcessingOutput;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TextProcessingServiceTest {

    @Test
    public void process확인() {
        TextProcessingUtil textUtil = mock(TextProcessingUtil.class);
        String mergeString = "mergestring";
        TextProcessingService textProcessingService = new TextProcessingService(textUtil);

        assertNotNull(textUtil);
        when(textUtil.parseString(any())).thenReturn("987654321aAbBcC");
        when(textUtil.merge(any(), any())).thenReturn(mergeString);

        TextProcessingInput input = new TextProcessingInput();
        input.setType(TextProcessingInput.Type.HTML);
        input.setUnit(1);
        input.setUrl("http://www.naver.com");
        TextProcessingOutput result = textProcessingService.process(input);
        assertTrue((mergeString).equals(result.getParse()));

        verify(textUtil, times(1)).parseString(any());
        verify(textUtil, times(15)).isNumber(anyByte());
        verify(textUtil, times(15)).isAlphabets(anyByte());
        verify(textUtil, times(1)).merge(any(), any());
    }
}