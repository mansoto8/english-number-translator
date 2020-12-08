package com.sampleapp.numbertranslator.exceptions;

/**
 * Exception thrown when number to be translated is too large
 */
public class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}
