package com.sampleapp.numbertranslator;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sampleapp.numbertranslator.core.NumberTranslator;
import com.sampleapp.numbertranslator.core.impl.NumberTranslatorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
