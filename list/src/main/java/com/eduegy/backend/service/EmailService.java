package com.eduegy.backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String body) {
        // Later: JavaMailSender / SMTP
        System.out.println("Sending email to " + to);
        System.out.println("Subject: " + subject);
        System.out.println(body);
    }
}
