package com.sampleapp.numbertranslator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains the string representing the number translated to english
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDTO {
    private String output;
}
