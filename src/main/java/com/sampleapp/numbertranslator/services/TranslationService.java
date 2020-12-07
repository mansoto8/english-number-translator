package com.sampleapp.numbertranslator.services;

import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;

import java.util.List;
import java.util.Set;

public interface TranslationService {

    OutputDTO translateNumber(Long number) throws InvalidNumberException;

    Set<TranslationDTO> getUserTranslations();
}
