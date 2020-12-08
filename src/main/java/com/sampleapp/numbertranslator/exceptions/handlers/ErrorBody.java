package com.sampleapp.numbertranslator.exceptions.handlers;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for all errors returned in the REST endpoints
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorBody {
    private String error;
    private String message;
    private String detail;
}
