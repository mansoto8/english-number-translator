package com.sampleapp.numbertranslator.exceptions.handlers;

import com.sampleapp.numbertranslator.exceptions.InvalidNumberException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Centralized management for all of the Exceptions generated in the application and their corresponding
 * mapping to an HTTP response.
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidNumberException.class)
    protected ResponseEntity<Object> handleAccountNotFoundException(
            Exception ex, WebRequest request) {
        ErrorBody errorBody = new ErrorBody("error-001", "Number is too large.", "Maximum is positive " +
                "or negative 9,999,999,999.");
        return handleExceptionInternal(ex, errorBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(
            Exception ex, WebRequest request) {
        ErrorBody errorBody = new ErrorBody("error-003", ex.getMessage(), "Internal Server Error");

        return handleExceptionInternal(ex, errorBody,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
