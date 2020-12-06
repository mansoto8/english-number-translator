package com.sampleapp.numbertranslator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationDTO {
    private Long number;
    private String translation;
}
