package com.example.htmlparser.service;

import com.example.htmlparser.model.TextProcessingInput;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.htmlparser.model.TextProcessingInput.Type.HTML;

@Slf4j
@Component
public class TextProcessingUtil {

    public boolean isNumber (byte inByte) {
        if ( inByte >= 48 && inByte <= 57 ) {
            return true;
        }
        return false;
    }
    public boolean isAlphabets (byte inByte) {
        if ( inByte >= 65 && inByte <= 90 || inByte >= 97 && inByte <= 122 ) {
            return true;
        }
        return false;
    }

    public String parseString(TextProcessingInput textProcessing) {
        String doc = null;
        try {
            if(textProcessing.getType() == HTML) {
                doc = Jsoup.connect(textProcessing.getUrl()).get().text();
            } else {
                doc = Jsoup.connect(textProcessing.getUrl()).get().html();
            }
        } catch (Exception ex) {
            doc = "Exception";
        }
        return doc;
    }

    public String merge(List<String> numbers , List<String> alphabets) {
        StringBuilder sb = new StringBuilder();
        int maxSize = numbers.size() > alphabets.size() ? numbers.size() : alphabets.size();
        for ( int i = 0 ; i < maxSize ; i ++ ) {
            if ( i < alphabets.size() ) {
                sb.append(alphabets.get(i));
            }
            if ( i < numbers.size() ) {
                sb.append(numbers.get(i));
            }
        }
        return sb.toString();
    }
}
