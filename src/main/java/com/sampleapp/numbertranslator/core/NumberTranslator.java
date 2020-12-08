package com.sampleapp.numbertranslator.core;

import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;

/**
 * Translates the number provided to its english text representation.
 */
public interface NumberTranslator {

    String translate(long number) throws InvalidNumberException;
}
