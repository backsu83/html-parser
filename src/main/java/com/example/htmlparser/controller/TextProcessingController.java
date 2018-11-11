package com.example.htmlparser.controller;

import com.example.htmlparser.model.TextProcessingInput;
import com.example.htmlparser.model.TextProcessingOutput;
import com.example.htmlparser.service.TextProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class TextProcessingController {

    private TextProcessingService service;

    public TextProcessingController(@Autowired TextProcessingService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String input(final Model model) {
        model.addAttribute("TextProcessingInput", new TextProcessingInput());
        model.addAttribute("TextProcessingOutput", new TextProcessingOutput());
        return "index";
    }

    @PostMapping("/")
    public String output(@Valid @ModelAttribute TextProcessingInput text , final Model model) {
        TextProcessingOutput out = service.process(text);
        model.addAttribute("TextProcessingInput", text);
        model.addAttribute("TextProcessingOutput", out);
        return "index";
    }
}