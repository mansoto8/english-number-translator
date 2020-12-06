package com.sampleapp.numbertranslator.services;

import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;

import java.util.List;

public interface TranslationService {

    OutputDTO translateNumber(Long number);

    List<TranslationDTO> getUserTranslations();
}
