package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;

public class NumberTranslatorImpl implements NumberTranslator {
    private String[] firstTen = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};
    private String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public String translate(long number) {
        String output = "";

        if (number / 10 < 1) {
            output = getNumberBetweenZeroAndTen(number);
        } else if (number / 10 < 10) {
            if (number < 20) {
                output = getNumberBetweenTenAndTwenty(number);
            } else {
                output = getNumberBetweenTwentyAndOneHundred(number);
            }
        } else if (number / 10 < 100 ) {
            output = getNumberBetweenOneHundredAndOneThousand(number);
        }

        return capitalize(output);
    }

    private String getNumberBetweenZeroAndTen(long number) {
        return firstTen[(int) number];
    }

    private String getNumberBetweenTenAndTwenty(long number) {
        int index = (int) number % 10;
        return teens[index];
    }

    private String getNumberBetweenTwentyAndOneHundred(long number) {
        int tensOfInput = (int) number / 10;
        int leftover = (int) number % 10;

        String text = tens[tensOfInput-2];

        if (leftover != 0) {
            text += " " + firstTen[leftover];
        }

        return text;
    }

    private String getNumberBetweenOneHundredAndOneThousand(long number) {
        int hundredsOfInput = (int) number / 100;
        int leftover = (int) number % 100;

        String text = firstTen[hundredsOfInput] + " hundred";

        if (leftover != 0) {
            if(leftover < 10) {
                text += " and " + getNumberBetweenZeroAndTen(leftover);
            } else if(leftover < 20) {
                text += " and " + getNumberBetweenTenAndTwenty(leftover);
            } else {
                text += " and " + getNumberBetweenTwentyAndOneHundred(leftover);
            }
        }

        return text;
    }

    private String capitalize(String number) {
        return number.substring(0, 1).toUpperCase() + number.substring(1);
    }
}
