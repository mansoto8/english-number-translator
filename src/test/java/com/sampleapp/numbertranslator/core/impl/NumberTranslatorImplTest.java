package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTranslatorImplTest {

    private String[] firstTen = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};
    private String[] betweenTwentyAndOneHundred = {"Twenty", "Twenty one", "Thirty two", "Forty three", "Fifty four",
            "Sixty five", "Seventy six", "Eighty seven", "Ninety eight", "Ninety nine"};

    NumberTranslator numberTranslator = new NumberTranslatorImpl();

    @Test
    public void translateNumber_firstTen() {
        for(int i = 0; i < 10; i++) {
            String output = numberTranslator.translate((long)i);

            assertEquals(firstTen[i], output);
        }
    }

    @Test
    public void translateNumber_betweenTenAndTwenty() {
        for(int i = 0; i < 10; i++) {
            String output = numberTranslator.translate(i+10);

            assertEquals(teens[i], output);
        }
    }

    @Test
    public void translateNumber_betweenTwentyAndOneHundred() {
        assertEquals(betweenTwentyAndOneHundred[0], numberTranslator.translate(20l));
        assertEquals(betweenTwentyAndOneHundred[1], numberTranslator.translate(21l));
        assertEquals(betweenTwentyAndOneHundred[2], numberTranslator.translate(32l));
        assertEquals(betweenTwentyAndOneHundred[3], numberTranslator.translate(43l));
        assertEquals(betweenTwentyAndOneHundred[4], numberTranslator.translate(54l));
        assertEquals(betweenTwentyAndOneHundred[5], numberTranslator.translate(65l));
        assertEquals(betweenTwentyAndOneHundred[6], numberTranslator.translate(76l));
        assertEquals(betweenTwentyAndOneHundred[7], numberTranslator.translate(87l));
        assertEquals(betweenTwentyAndOneHundred[8], numberTranslator.translate(98l));
        assertEquals(betweenTwentyAndOneHundred[9], numberTranslator.translate(99l));

    }
}
