package com.example.MyMemoryTranslation.controller;

import com.example.MyMemoryTranslation.dto.TranslationRequest;
import com.example.MyMemoryTranslation.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class TranslationController {
    private final TranslatorService translatorService;

    @Autowired
    public TranslationController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @PostMapping("/translate")
    public ResponseEntity<String> translate(@RequestBody TranslationRequest request) {
        try {
            String translatedText = translatorService.translateText(request.getText(), request.getTargetLanguage());
            return ResponseEntity.ok(translatedText);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Translation error");
        }
    }
}
