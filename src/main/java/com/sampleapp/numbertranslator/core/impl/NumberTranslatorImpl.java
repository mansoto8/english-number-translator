package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.ConnectorType;
import com.sampleapp.numbertranslator.core.NumberTranslator;

public class NumberTranslatorImpl implements NumberTranslator {
    private String[] firstTen = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};
    private String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public String translate(long number) {
        String output;

        if (number < 1000) {
            output = getNumberLessThanOneThousand(number, true);
        } else if(number < 1000000) {
            output = getNumberBetweenOneThousandAndOneMillion(number);
        } else {
            output = getNumberBetweenOneMillionAndOneThousandMillion(number);
        }

        return capitalize(output);
    }

    private String getNumberLessThanOneThousand(long number, boolean andConnector) {
        String output = "";

        if (number < 100) {
            output = getNumberLessThanOneHundred(number);
        } else if (number / 10 < 100) {
            output = getNumberBetweenOneHundredAndOneThousand(number, andConnector);
        }

        return output;
    }

    private String getNumberLessThanOneHundred(long number) {
        String output = "";

        if (number < 10) {
            output += getNumberBetweenZeroAndTen(number);
        } else if (number < 20) {
            output += getNumberBetweenTenAndTwenty(number);
        } else {
            output += getNumberBetweenTwentyAndOneHundred(number);
        }

        return output;
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

        String text = tens[tensOfInput - 2];

        if (leftover != 0) {
            text += " " + firstTen[leftover];
        }


        return text;
    }

    private String getNumberBetweenOneHundredAndOneThousand(long number, boolean andConnector) {
        int hundredsOfInput = (int) number / 100;
        int leftover = (int) number % 100;

        String text = firstTen[hundredsOfInput] + " hundred";

        ConnectorType connector = andConnector ? ConnectorType.AND : ConnectorType.SPACE;

        if (leftover != 0) {
            text += connector.getText();
            text += getNumberLessThanOneHundred(leftover);
        }

        return text;
    }

    private String getNumberBetweenOneThousandAndOneMillion(long number) {
        long thousandsOfInput = number >= 1000 ? number / 1000 : 0;
        long leftoverOfInput = number % 1000;

        String text = "";
        if(thousandsOfInput != 0) {
            text = getNumberLessThanOneThousand(thousandsOfInput, false) + " thousand";
        }

        String leftover = "";
        if (leftoverOfInput != 0) {
            leftover = leftoverOfInput < 100 ? ConnectorType.AND.getText() : ConnectorType.SPACE.getText();
            leftover += getNumberLessThanOneThousand(leftoverOfInput, true);
        }

        if (!leftover.isBlank()) {
            text += leftover;
        }

        return text;
    }

    private String getNumberBetweenOneMillionAndOneThousandMillion(long number) {
        long millionsOfInput = number / 1000000;
        long leftoverOfInput = number % 1000000;

        String text = getNumberLessThanOneThousand(millionsOfInput, false);
        String leftover = "";

        if (leftoverOfInput != 0) {
            leftover = leftoverOfInput < 1000 ? ConnectorType.EMPTY.getText() : ConnectorType.SPACE.getText();
            leftover += getNumberBetweenOneThousandAndOneMillion(leftoverOfInput);
        }

        text += " million";
        if (!leftover.isBlank()) {
            text += leftover;
        }

        return text;
    }

    private String capitalize(String number) {
        return number.substring(0, 1).toUpperCase() + number.substring(1);
    }
}
