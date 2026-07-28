package com.eduegy.backend.controller;

import com.eduegy.backend.model.User;
import com.eduegy.backend.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final ProgressService progressService;

    public DashboardController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping
    public User dashboard(Principal principal) {
        return progressService.getMyProgress(principal);
    }
}
