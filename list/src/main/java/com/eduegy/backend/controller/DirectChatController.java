package com.eduegy.backend.controller;

import com.eduegy.backend.dto.ChatMessage;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.UserRepository;
import com.eduegy.backend.service.TranslateService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class DirectChatController {

    private final UserRepository userRepository;
    private final TranslateService translateService;

    public DirectChatController(UserRepository userRepository, TranslateService translateService) {
        this.userRepository = userRepository;
        this.translateService = translateService;
    }

    // convId is a client-generated string like "u12_u34" (sorted)
    @MessageMapping("/dm/{convId}")
    @SendTo("/topic/dm/{convId}")
    public ChatMessage dm(@DestinationVariable String convId, ChatMessage incoming) {
        User user = null;
        if (incoming.getSenderEmail() != null) {
            user = userRepository.findByEmail(incoming.getSenderEmail()).orElse(null);
        }

        ChatMessage out = new ChatMessage();
        out.setSenderEmail(incoming.getSenderEmail());
        out.setSenderName(user != null ? user.getFullName() : (incoming.getSenderName() != null ? incoming.getSenderName() : "User"));
        out.setSenderRole(user != null ? user.getRole() : incoming.getSenderRole());
        out.setText(incoming.getText());
        out.setTimestamp(Instant.now());

        String lang = incoming.getSubtitleLang();
        out.setSubtitleLang(lang);
        out.setSubtitleText(translateService.translate(incoming.getText(), lang));
        return out;
    }
}
