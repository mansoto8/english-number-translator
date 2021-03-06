package com.sampleapp.numbertranslator;

import com.sampleapp.numbertranslator.core.NumberTranslator;
import com.sampleapp.numbertranslator.core.impl.NumberTranslatorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application that expose rest endpoints for managing and executing translation of numbers to its english textual
 * representation. It also deploys a web UI that allows to easily interact with the rest endpoints.
 */
@SpringBootApplication
public class NumberTranslatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberTranslatorApplication.class, args);
    }

    @Bean
    public NumberTranslator numberTranslator() {

        return new NumberTranslatorImpl();
    }
}
