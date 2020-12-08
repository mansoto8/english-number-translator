package com.sampleapp.numbertranslator.core.impl;

import com.sampleapp.numbertranslator.core.ConnectorType;
import com.sampleapp.numbertranslator.core.NumberTranslator;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;

public class NumberTranslatorImpl implements NumberTranslator {
    private static final long ONE_THOUSAND = 1000l;
    private static final long ONE_MILLION = 1000000l;
    private static final long ONE_BILLION = 1000000000l;
    private static final long MAX_NUMBER_ACCEPTED = 9999999999l;

    private String[] firstTen = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};
    private String[] tens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public String translate(long number) throws InvalidNumberException {
        if (Math.abs(number) > MAX_NUMBER_ACCEPTED) {
            throw new InvalidNumberException("Number is too large. Maximum is positive or negative 9,999,999,999.");
        }

        String output = "";
        if (number < 0) {
            output += "Negative ";
            number *= -1;
        }

        if (number < ONE_THOUSAND) {
            output += getNumberLessThanOneThousand(number, true);
        } else if (number < ONE_MILLION) {
            output += getNumberBetweenOneThousandAndOneMillion(number);
        } else if (number < ONE_BILLION) {
            output += getNumberBetweenOneMillionAndOneBillion(number);
        } else {
            output += getNumberBetweenOneBillionAndTenBillion(number);
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
        long thousandsOfInput = number >= ONE_THOUSAND ? number / ONE_THOUSAND : 0;
        long leftoverOfInput = number % ONE_THOUSAND;

        String text = "";
        if (thousandsOfInput != 0) {
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

    private String getNumberBetweenOneMillionAndOneBillion(long number) {
        long millionsOfInput = number / ONE_MILLION;
        long leftoverOfInput = number % ONE_MILLION;

        String text = "";
        if (millionsOfInput != 0) {
            text = getNumberLessThanOneThousand(millionsOfInput, false) + " million";
        }
        String leftover = "";

        if (leftoverOfInput != 0) {
            leftover = leftoverOfInput < ONE_THOUSAND ? ConnectorType.EMPTY.getText() : ConnectorType.SPACE.getText();
            leftover += getNumberBetweenOneThousandAndOneMillion(leftoverOfInput);
        }

        if (!leftover.isBlank()) {
            text += leftover;
        }

        return text;
    }

    private String getNumberBetweenOneBillionAndTenBillion(long number) {
        long billionsOfInput = number / ONE_BILLION;
        long leftoverOfInput = number % ONE_BILLION;

        String text = getNumberBetweenZeroAndTen(billionsOfInput);
        String leftover = "";

        if (leftoverOfInput != 0) {
            leftover = leftoverOfInput < ONE_MILLION ? ConnectorType.EMPTY.getText() : ConnectorType.SPACE.getText();
            leftover += getNumberBetweenOneMillionAndOneBillion(leftoverOfInput);
        }

        text += " billion";
        if (!leftover.isBlank()) {
            text += leftover;
        }

        return text;
    }

    private String capitalize(String number) {
        return number.substring(0, 1).toUpperCase() + number.substring(1);
    }
}
