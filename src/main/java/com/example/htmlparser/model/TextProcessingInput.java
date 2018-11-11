package com.example.htmlparser.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class TextProcessingInput {

    public static enum Type {
        TEXT,
        HTML
    }

    @NotNull
    @Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String url;

    private Type type = Type.HTML;

    @NotNull
    @Min(value = 1)
    private int unit;

}
