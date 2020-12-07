package com.sampleapp.numbertranslator.core;

import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;

public interface NumberTranslator {

    String translate(long number) throws InvalidNumberException;
}
