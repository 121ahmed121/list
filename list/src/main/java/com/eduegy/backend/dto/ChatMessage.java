package com.eduegy.backend.dto;

import com.eduegy.backend.model.Role;

import java.time.Instant;

public class ChatMessage {
    private String senderEmail;
    private String senderName;
    private Role senderRole;
    private String text;
    private Instant timestamp = Instant.now();

    // optional translated subtitle
    private String subtitleLang;
    private String subtitleText;

    public ChatMessage() {}

    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public Role getSenderRole() { return senderRole; }
    public void setSenderRole(Role senderRole) { this.senderRole = senderRole; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public String getSubtitleLang() { return subtitleLang; }
    public void setSubtitleLang(String subtitleLang) { this.subtitleLang = subtitleLang; }
    public String getSubtitleText() { return subtitleText; }
    public void setSubtitleText(String subtitleText) { this.subtitleText = subtitleText; }
}
