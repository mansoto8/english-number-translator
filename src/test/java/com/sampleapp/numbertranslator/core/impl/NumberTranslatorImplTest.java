package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTranslatorImplTest {

    NumberTranslator numberTranslator = new NumberTranslatorImpl();

    @Test
    public void translateNumber_firstTen() {
        String[] expectedValues = {"Zero", "One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine"};

        for (int i = 0; i < 10; i++) {
            String output = numberTranslator.translate((long) i);

            assertEquals(expectedValues[i], output);
        }
    }

    @Test
    public void translateNumber_betweenTenAndTwenty() {
        String[] expectedValues = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen"};

        for (int i = 0; i < 10; i++) {
            String output = numberTranslator.translate(i + 10);

            assertEquals(expectedValues[i], output);
        }
    }

    @Test
    public void translateNumber_betweenTwentyAndOneHundred() {
        String[] expectedValues = {
                "Twenty",
                "Twenty one",
                "Thirty two",
                "Forty three",
                "Fifty four",
                "Sixty five",
                "Seventy six",
                "Eighty seven",
                "Ninety eight",
                "Ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(20l));
        assertEquals(expectedValues[1], numberTranslator.translate(21l));
        assertEquals(expectedValues[2], numberTranslator.translate(32l));
        assertEquals(expectedValues[3], numberTranslator.translate(43l));
        assertEquals(expectedValues[4], numberTranslator.translate(54l));
        assertEquals(expectedValues[5], numberTranslator.translate(65l));
        assertEquals(expectedValues[6], numberTranslator.translate(76l));
        assertEquals(expectedValues[7], numberTranslator.translate(87l));
        assertEquals(expectedValues[8], numberTranslator.translate(98l));
        assertEquals(expectedValues[9], numberTranslator.translate(99l));
    }

    @Test
    public void translateNumber_betweenOneHundredAndOneThousand() {
        String[] expectedValues = {
                "One hundred",
                "One hundred and one",
                "Two hundred and ten",
                "Three hundred and eleven",
                "Four hundred and twenty one",
                "Five hundred",
                "Six hundred and thirty one",
                "Seven hundred",
                "Eight hundred",
                "Nine hundred",
                "Nine hundred and ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(100l));
        assertEquals(expectedValues[1], numberTranslator.translate(101l));
        assertEquals(expectedValues[2], numberTranslator.translate(210l));
        assertEquals(expectedValues[3], numberTranslator.translate(311l));
        assertEquals(expectedValues[4], numberTranslator.translate(421l));
        assertEquals(expectedValues[5], numberTranslator.translate(500l));
        assertEquals(expectedValues[6], numberTranslator.translate(631l));
        assertEquals(expectedValues[7], numberTranslator.translate(700l));
        assertEquals(expectedValues[8], numberTranslator.translate(800l));
        assertEquals(expectedValues[9], numberTranslator.translate(900l));
        assertEquals(expectedValues[10], numberTranslator.translate(999l));
    }

    @Test
    public void translateNumber_betweenOneThousandAndOneHundredThousand() {
        String[] expectedValues = {
                "One thousand",
                "Ten thousand and two",
                "Thirteen thousand and forty",
                "One hundred thousand five hundred",
                "Two hundred thousand six hundred and seven",
                "Three hundred thousand eight hundred and ninety",
                "Four hundred one thousand",
                "Five hundred twenty thousand",
                "Six hundred thirty four thousand",
                "Seven hundred fifty six thousand and seven",
                "Eight hundred eighty nine thousand one hundred and two",
                "Nine hundred ninety nine thousand nine hundred and ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(1000l));
        assertEquals(expectedValues[1], numberTranslator.translate(10002l));
        assertEquals(expectedValues[2], numberTranslator.translate(13040l));
        assertEquals(expectedValues[3], numberTranslator.translate(100500l));
        assertEquals(expectedValues[4], numberTranslator.translate(200607l));
        assertEquals(expectedValues[5], numberTranslator.translate(300890l));
        assertEquals(expectedValues[6], numberTranslator.translate(401000l));
        assertEquals(expectedValues[7], numberTranslator.translate(520000l));
        assertEquals(expectedValues[8], numberTranslator.translate(634000l));
        assertEquals(expectedValues[9], numberTranslator.translate(756007l));
        assertEquals(expectedValues[10], numberTranslator.translate(889102l));
        assertEquals(expectedValues[11], numberTranslator.translate(999999l));
    }

    @Test
    public void translateNumber_betweenOneHundredThousandAndOneBillion() {

        String[] expectedValues = {
                "One million",
                "Twenty million and one",
                "Thirty million two hundred and three",
                "Forty million four thousand",
                "Fifty million five hundred six thousand",
                "Sixty million seven hundred eight thousand nine hundred and one",
                "Seven hundred sixty seven million",
                "Eight hundred two million",
                "Nine hundred thirty million forty thousand and fifty",
                "Nine hundred ninety nine million nine hundred ninety nine thousand nine hundred and ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(1000000l));
        assertEquals(expectedValues[1], numberTranslator.translate(20000001l));
        assertEquals(expectedValues[2], numberTranslator.translate(30000203l));
        assertEquals(expectedValues[3], numberTranslator.translate(40004000l));
        assertEquals(expectedValues[4], numberTranslator.translate(50506000l));
        assertEquals(expectedValues[5], numberTranslator.translate(60708901l));
        assertEquals(expectedValues[6], numberTranslator.translate(767000000l));
        assertEquals(expectedValues[7], numberTranslator.translate(802000000l));
        assertEquals(expectedValues[8], numberTranslator.translate(930040050l));
        assertEquals(expectedValues[9], numberTranslator.translate(999999999l));
    }

    @Test
    public void translateNumber_betweenOneBillionAndTenBillion() {
        String[] expectedValues = {
                "One billion",
                "Two billion and one",
                "Three billion and twenty",
                "Four billion three hundred",
                "Four billion four thousand",
                "Five billion four thousand and one",
                "Six billion fifty thousand six hundred",
                "Seven billion seven hundred thousand",
                "Eight billion eight million and one",
                "Nine billion nine hundred million",
                "Nine billion nine hundred ninety nine million nine hundred ninety nine thousand nine hundred and ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(1000000000l));
        assertEquals(expectedValues[1], numberTranslator.translate(2000000001l));
        assertEquals(expectedValues[2], numberTranslator.translate(3000000020l));
        assertEquals(expectedValues[3], numberTranslator.translate(4000000300l));
        assertEquals(expectedValues[4], numberTranslator.translate(4000004000l));
        assertEquals(expectedValues[5], numberTranslator.translate(5000004001l));
        assertEquals(expectedValues[6], numberTranslator.translate(6000050600l));
        assertEquals(expectedValues[7], numberTranslator.translate(7000700000l));
        assertEquals(expectedValues[8], numberTranslator.translate(8008000001l));
        assertEquals(expectedValues[9], numberTranslator.translate(9900000000l));
        assertEquals(expectedValues[10], numberTranslator.translate(9999999999l));
    }

    @Test
    public void translateNumber_negativeNumbers() {
        String[] expectedValues = {
                "Negative two",
                "Negative fourteen",
                "Negative twenty one",
                "Negative three hundred and eleven",
                "Negative three hundred thousand eight hundred and ninety",
                "Negative sixty million seven hundred eight thousand nine hundred and one",
                "Negative nine billion nine hundred ninety nine million nine hundred ninety nine thousand nine hundred and ninety nine"};

        assertEquals(expectedValues[0], numberTranslator.translate(-2l));
        assertEquals(expectedValues[1], numberTranslator.translate(-14l));
        assertEquals(expectedValues[2], numberTranslator.translate(-21l));
        assertEquals(expectedValues[3], numberTranslator.translate(-311l));
        assertEquals(expectedValues[4], numberTranslator.translate(-300890l));
        assertEquals(expectedValues[5], numberTranslator.translate(-60708901l));
        assertEquals(expectedValues[6], numberTranslator.translate(-9999999999l));
    }
}
