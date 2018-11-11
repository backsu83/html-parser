package com.example.htmlparser.controller;

import com.example.htmlparser.model.TextProcessingOutput;
import com.example.htmlparser.service.TextProcessingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TextProcessingController.class)
public class TextProcessingControllerTest {

    @MockBean
    private TextProcessingService textService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {

        TextProcessingOutput output = new TextProcessingOutput();
        output.setRemainder("remainder");
        output.setParse("parse");
        when(textService.process(any())).thenReturn(output);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("url", "http://www.naver.com")
                .param("type", "HTML")
                .param("unit", "1"))
                .andExpect(status().isOk());
        verify(textService, times(1)).process(any());
    }

}