package com.sampleapp.numbertranslator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationDTO implements Comparable<TranslationDTO> {
    private Long number;
    private String translation;

    @Override
    public int compareTo(TranslationDTO translationDTO) {
        return getNumber().compareTo(translationDTO.getNumber());
    }
}
