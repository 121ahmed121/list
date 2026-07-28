package com.eduegy.backend.service;

import com.eduegy.backend.dto.LoginResponse;
import com.eduegy.backend.dto.RegisterRequest;
import com.eduegy.backend.model.Role;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.UserRepository;
import com.eduegy.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already used");
        }

        Role role = Role.STUDENT;
        if (req.getRole() != null && !req.getRole().isBlank()) {
            try {
                role = Role.valueOf(req.getRole().trim().toUpperCase());
            } catch (Exception ignored) {
                role = Role.STUDENT;
            }
        }

        // Prevent self-registering privileged accounts
        if (role == Role.ADMIN || role == Role.MODERATOR) {
            role = Role.STUDENT;
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(role);

        User saved = userRepository.save(user);
        String token = jwtService.generateToken(saved.getEmail());

        return new LoginResponse(token, saved.getId(), saved.getEmail(), saved.getFullName(), saved.getRole());
    }

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(token, user.getId(), user.getEmail(), user.getFullName(), user.getRole());
    }
}
