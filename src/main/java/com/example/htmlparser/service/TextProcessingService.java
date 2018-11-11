package com.example.htmlparser.service;

import com.example.htmlparser.model.TextProcessingInput;
import com.example.htmlparser.model.TextProcessingOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TextProcessingService {

    private TextProcessingUtil textUtil;

    public TextProcessingService(TextProcessingUtil textUtil) {
        this.textUtil = textUtil;
    }

    /**
     * 파싱된 html 을 byte변환후 영문 숫자 분류
     * 오름차순 정렬하되 영문은 알파벳 대-소문자로 정렬
     * 정렬된 데이터를 영어-숫자 순서로 반복 저장
     * 입력 받수 숫자(unit)로 결과 문자열을 나누고 나머지가 있다면 별도표기
     * @param input
     * @return TextProcessingOutput
     */
    public TextProcessingOutput process(TextProcessingInput input) {

        log.info("input : {}" , input);
        String text = textUtil.parseString(input);
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        byte[] contentBytes = text.getBytes();
        for ( byte contentByte : contentBytes) {
            if ( textUtil.isNumber(contentByte) ) {
                numbers.add(String.valueOf((char) (contentByte & 0xFF)));
            } else if ( textUtil.isAlphabets(contentByte) ){
                alphabets.add(String.valueOf((char) (contentByte & 0xFF)));
            }
        }

        Collections.sort(numbers);
        Collections.sort(alphabets);
        Collections.sort(alphabets , String.CASE_INSENSITIVE_ORDER);
        String result = textUtil.merge(numbers , alphabets );

        int quotient = (result.length() / input.getUnit()) * input.getUnit();
        String parse = result.substring(0 , quotient);
        String remainder = result.substring(quotient , result.length());
        TextProcessingOutput output = new TextProcessingOutput();
        output.setParse(parse);
        output.setRemainder(remainder);
        log.info("parse count:{} remainder count:{}" , parse.length() , remainder.length());
        return output;
    }

}
