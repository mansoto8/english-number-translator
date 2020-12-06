package com.sampleapp.numbertranslator.controllers;

import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/numbers")
public interface TranslatorController {

    @PostMapping("/{number}")
    ResponseEntity<OutputDTO> translateNumber(@PathVariable("number") Long number);

    @GetMapping
    ResponseEntity<List<TranslationDTO>> getTranslationHistory();
}
