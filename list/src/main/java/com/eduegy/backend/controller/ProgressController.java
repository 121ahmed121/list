package com.eduegy.backend.controller;

import com.eduegy.backend.model.User;
import com.eduegy.backend.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping
    public User myProgress(Principal principal) {
        return progressService.getMyProgress(principal);
    }

    @PostMapping("/{courseId}")
    public String update(
            Principal principal,
            @PathVariable Long courseId,
            @RequestParam int progress
    ) {
        return progressService.updateProgress(principal, courseId, progress);
    }
}
