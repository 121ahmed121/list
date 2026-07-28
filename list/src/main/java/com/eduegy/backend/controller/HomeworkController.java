package com.eduegy.backend.controller;

import com.eduegy.backend.model.Homework;
import com.eduegy.backend.model.HomeworkSubmission;
import com.eduegy.backend.service.HomeworkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/homework")
@CrossOrigin
public class HomeworkController {

    private final HomeworkService service;

    public HomeworkController(HomeworkService service) {
        this.service = service;
    }

    @GetMapping
    public List<Homework> list(@RequestParam("courseId") Long courseId) {
        return service.listByCourse(courseId);
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','MODERATOR')")
    @PostMapping
    public Homework create(@RequestBody Map<String, Object> body, Principal principal) {
        Long courseId = ((Number) body.get("courseId")).longValue();
        String title = (String) body.getOrDefault("title", "Homework");
        String description = (String) body.getOrDefault("description", "");
        Instant dueAt = null;
        if (body.get("dueAt") != null) {
            try { dueAt = Instant.parse(String.valueOf(body.get("dueAt"))); } catch (Exception ignored) {}
        }
        return service.create(courseId, title, description, dueAt, principal.getName());
    }

    @PostMapping("/{id}/submit")
    public HomeworkSubmission submit(@PathVariable("id") Long id,
                                     @RequestParam("file") MultipartFile file,
                                     Principal principal) throws IOException {
        return service.submit(id, principal.getName(), file);
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','MODERATOR')")
    @GetMapping("/{id}/submissions")
    public List<HomeworkSubmission> submissions(@PathVariable("id") Long id) {
        return service.listSubmissions(id);
    }

    @GetMapping("/my-submissions")
    public List<HomeworkSubmission> mySubmissions(Principal principal) {
        return service.mySubmissions(principal.getName());
    }
}
