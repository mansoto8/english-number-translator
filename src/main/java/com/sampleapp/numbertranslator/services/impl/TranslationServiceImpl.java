package com.sampleapp.numbertranslator.services.impl;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;
import com.sampleapp.numbertranslator.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Service
public class TranslationServiceImpl implements TranslationService {

    private Set<TranslationDTO> userTranslations = new TreeSet<>();

    @Autowired
    NumberTranslator numberTranslator;

    /**
     * {@inheritDoc}
     */
    public OutputDTO translateNumber(Long number) throws InvalidNumberException {
        String translation;

        Optional<TranslationDTO> translationDTO =
                userTranslations.stream().filter(t -> t.getNumber().equals(number)).findFirst();

        if (translationDTO.isPresent()) {
            translation = translationDTO.get().getTranslation();
        } else {
            translation = numberTranslator.translate(number);
            userTranslations.add(new TranslationDTO(number, translation));
        }

        return new OutputDTO(translation);
    }

    /**
     * {@inheritDoc}
     */
    public Set<TranslationDTO> getUserTranslations() {
        return userTranslations;
    }
}
