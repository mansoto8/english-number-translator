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
    private String[] betweenOneHundredAndOneThousand = {"One hundred", "One hundred and one", "Two hundred and ten",
            "Three hundred and eleven", "Four hundred and twenty one", "Five hundred", "Six hundred and thirty one",
            "Seven hundred", "Eight hundred", "Nine hundred", "Nine hundred and ninety nine"};
    private String[] betweenOneThousandAndOneHundredThousand = {"One thousand", "Ten thousand and two",
            "Thirteen thousand and forty", "One hundred thousand five hundred", "Two hundred thousand six hundred and seven",
            "Three hundred thousand eight hundred and ninety", "Four hundred one thousand",
            "Five hundred twenty thousand", "Six hundred thirty four thousand",
            "Seven hundred fifty six thousand and seven", "Eight hundred eighty nine thousand one hundred and two",
            "Nine hundred ninety nine thousand nine hundred and ninety nine"};

    NumberTranslator numberTranslator = new NumberTranslatorImpl();

    @Test
    public void translateNumber_firstTen() {
        for (int i = 0; i < 10; i++) {
            String output = numberTranslator.translate((long) i);

            assertEquals(firstTen[i], output);
        }
    }

    @Test
    public void translateNumber_betweenTenAndTwenty() {
        for (int i = 0; i < 10; i++) {
            String output = numberTranslator.translate(i + 10);

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

    @Test
    public void translateNumber_betweenOneHundredAndOneThousand() {
        assertEquals(betweenOneHundredAndOneThousand[0], numberTranslator.translate(100l));
        assertEquals(betweenOneHundredAndOneThousand[1], numberTranslator.translate(101l));
        assertEquals(betweenOneHundredAndOneThousand[2], numberTranslator.translate(210l));
        assertEquals(betweenOneHundredAndOneThousand[3], numberTranslator.translate(311l));
        assertEquals(betweenOneHundredAndOneThousand[4], numberTranslator.translate(421l));
        assertEquals(betweenOneHundredAndOneThousand[5], numberTranslator.translate(500l));
        assertEquals(betweenOneHundredAndOneThousand[6], numberTranslator.translate(631l));
        assertEquals(betweenOneHundredAndOneThousand[7], numberTranslator.translate(700l));
        assertEquals(betweenOneHundredAndOneThousand[8], numberTranslator.translate(800l));
        assertEquals(betweenOneHundredAndOneThousand[9], numberTranslator.translate(900l));
        assertEquals(betweenOneHundredAndOneThousand[10], numberTranslator.translate(999l));
    }

    @Test
    public void translateNumber_betweenOneThousandAndOneHundredThousand() {
        assertEquals(betweenOneThousandAndOneHundredThousand[0], numberTranslator.translate(1000l));
        assertEquals(betweenOneThousandAndOneHundredThousand[1], numberTranslator.translate(10002l));
        assertEquals(betweenOneThousandAndOneHundredThousand[2], numberTranslator.translate(13040l));
        assertEquals(betweenOneThousandAndOneHundredThousand[3], numberTranslator.translate(100500l));
        assertEquals(betweenOneThousandAndOneHundredThousand[4], numberTranslator.translate(200607l));
        assertEquals(betweenOneThousandAndOneHundredThousand[5], numberTranslator.translate(300890l));
        assertEquals(betweenOneThousandAndOneHundredThousand[6], numberTranslator.translate(401000l));
        assertEquals(betweenOneThousandAndOneHundredThousand[7], numberTranslator.translate(520000l));
        assertEquals(betweenOneThousandAndOneHundredThousand[8], numberTranslator.translate(634000l));
        assertEquals(betweenOneThousandAndOneHundredThousand[9], numberTranslator.translate(756007l));
        assertEquals(betweenOneThousandAndOneHundredThousand[10], numberTranslator.translate(889102l));
        assertEquals(betweenOneThousandAndOneHundredThousand[11], numberTranslator.translate(999999l));
    }
}
