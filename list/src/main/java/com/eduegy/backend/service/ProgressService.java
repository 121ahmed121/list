package com.eduegy.backend.service;

import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProgressService {

    private final UserRepository userRepository;

    public ProgressService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getMyProgress(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String updateProgress(Principal principal, Long courseId, int progress) {
        // for now just return success (you can add DB logic later)
        return "Progress updated";
    }
}
