package com.example.MyMemoryTranslation.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TranslatorConfig {
    public String getApiBaseUrl() {
        return "https://mymemory.translated.net";
    }
}
