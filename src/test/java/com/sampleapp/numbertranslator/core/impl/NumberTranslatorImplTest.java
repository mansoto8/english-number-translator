package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTranslatorImplTest {

    private String[] firstTen = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};

    @InjectMocks
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
}
