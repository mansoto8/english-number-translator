package com.sampleapp.numbertranslator.services;

import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;

import java.util.Set;

/**
 * Service for executing and managing translation of numbers to english
 */
public interface TranslationService {

    /**
     * Translates the number provided to its english textual representation. It only allows integer numbers between
     * - 9,999,999,999 and 9,999,999,999.
     *
     * @param number Number to be translated
     * @return The textual representation of the number
     * @throws InvalidNumberException If the number exceeds the limit
     */
    OutputDTO translateNumber(Long number) throws InvalidNumberException;

    /**
     * Returns the translation history requested by the user in the same HTTP session
     *
     * @return List of translations requested by the user in the session (Number - text pairs)
     */
    Set<TranslationDTO> getUserTranslations();
}
