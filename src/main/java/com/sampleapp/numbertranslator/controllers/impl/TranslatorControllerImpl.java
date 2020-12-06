package com.sampleapp.numbertranslator.controllers.impl;

import com.sampleapp.numbertranslator.controllers.TranslatorController;
import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class TranslatorControllerImpl implements TranslatorController {

    @Override
    public ResponseEntity<OutputDTO> translateNumber(@PathVariable("number") Long number) {
        return null;

    }


    @Override
    public ResponseEntity<List<TranslationDTO>> getTranslationHistory() {
        return null;
    }
}
