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
public class GlobalChatController {

    private final UserRepository userRepository;
    private final TranslateService translateService;

    public GlobalChatController(UserRepository userRepository, TranslateService translateService) {
        this.userRepository = userRepository;
        this.translateService = translateService;
    }

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/chat/{room}")
    public ChatMessage chat(@DestinationVariable String room, ChatMessage incoming) {
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
