package com.sampleapp.numbertranslator.services.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SessionScope
@Service
public class TranslationServiceImpl implements TranslationService {

    private Set<TranslationDTO> userTranslations;

    @Autowired
    NumberTranslator numberTranslator;

    public OutputDTO translateNumber(Long number) {
        return new OutputDTO(numberTranslator.translate(number));
    }

    public List<TranslationDTO> getUserTranslations() {
        return new ArrayList<>();
    }
}
