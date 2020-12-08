package com.sampleapp.numbertranslator.controllers.impl;

import com.sampleapp.numbertranslator.controllers.TranslatorController;
import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;
import com.sampleapp.numbertranslator.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TranslatorControllerImpl implements TranslatorController {

    @Autowired
    TranslationService translationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<OutputDTO> translateNumber(Long number) throws InvalidNumberException {
        OutputDTO outputDTO = translationService.translateNumber(number);

        return ResponseEntity.ok(outputDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Set<TranslationDTO>> getTranslationHistory() {
        Set<TranslationDTO> translations = translationService.getUserTranslations();

        return ResponseEntity.ok(translations);
    }
}
