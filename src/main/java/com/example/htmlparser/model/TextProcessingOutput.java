package com.example.htmlparser.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TextProcessingOutput {
    private String parse;
    private String remainder;
}
