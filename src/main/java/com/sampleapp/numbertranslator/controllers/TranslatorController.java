package com.sampleapp.numbertranslator.controllers;

import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * REST endpoint for executing and managing translation of numbers to english
 */
@RestController
@RequestMapping("/api/numbers")
public interface TranslatorController {

    /**
     * Translates the number provided to its english textual representation. It only allows integer numbers between
     * - 9,999,999,999 and 9,999,999,999.
     *
     * @param number Number to be translated
     * @return The textual representation of the number
     * @throws InvalidNumberException If the number exceeds the limit
     */
    @PostMapping("/{number}")
    ResponseEntity<OutputDTO> translateNumber(@PathVariable("number") Long number) throws InvalidNumberException;

    /**
     * Returns the translation history requested by the user in the same HTTP session
     *
     * @return List of translations requested by the user in the session (Number - text pairs)
     */
    @GetMapping
    ResponseEntity<Set<TranslationDTO>> getTranslationHistory();
}
