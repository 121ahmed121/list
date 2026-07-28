package com.eduegy.backend.service;

import org.springframework.stereotype.Service;

@Service
public class TranslateService {

    // NOTE: This is a stub translator (offline). Replace with real API later.
    // For now: returns the same text, but marks the target language so the UI shows it changed.
    public String translate(String text, String targetLang) {
        if (text == null) return "";
        if (targetLang == null || targetLang.isBlank() || targetLang.equalsIgnoreCase("auto")) {
            return text;
        }
        String lang = targetLang.trim().toUpperCase();
        return "[" + lang + "] " + text;
    }
}
