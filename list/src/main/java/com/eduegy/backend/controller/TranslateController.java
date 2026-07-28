package com.eduegy.backend.controller;

import com.eduegy.backend.service.TranslateService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/translate")
@CrossOrigin
public class TranslateController {

    private final TranslateService translateService;

    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping
    public Map<String, Object> translate(@RequestBody Map<String, String> body) {
        String text = body.getOrDefault("text", "");
        String target = body.getOrDefault("targetLang", "auto");
        return Map.of(
                "targetLang", target,
                "translatedText", translateService.translate(text, target)
        );
    }
}
