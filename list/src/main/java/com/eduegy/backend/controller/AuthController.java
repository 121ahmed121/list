package com.eduegy.backend.controller;

import com.eduegy.backend.dto.LoginRequest;
import com.eduegy.backend.dto.LoginResponse;
import com.eduegy.backend.dto.RegisterRequest;
import com.eduegy.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public LoginResponse register(@Valid @RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req.getEmail(), req.getPassword());
    }
}
