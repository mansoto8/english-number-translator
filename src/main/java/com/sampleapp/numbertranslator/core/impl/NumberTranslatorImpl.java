package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;

public class NumberTranslatorImpl implements NumberTranslator {

    private String[] firstTen = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};

    public String translate(long number) {

        String output = "";
        if (number / 10 < 1) {
            output = getNumberBetweenTen(number);
        } else if (number / 10 < 100) {
            if (number < 20) {
                output = getNumberBetweenTenAndTwenty(number);
            } else {

            }
        }

        return capitalize(output);
    }

    private String getNumberBetweenTen(long number) {
        return firstTen[(int) number];
    }

    private String getNumberBetweenTenAndTwenty(long number) {
        int index = (int) number % 10;
        return teens[index];
    }

    private String capitalize(String number) {
        return number.substring(0, 1).toUpperCase() + number.substring(1);
    }
}
