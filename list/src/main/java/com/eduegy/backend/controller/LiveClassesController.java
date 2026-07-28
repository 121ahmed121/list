package com.eduegy.backend.controller;

import com.eduegy.backend.model.LiveClass;
import com.eduegy.backend.model.LiveClassFile;
import com.eduegy.backend.service.LiveClassService;
import jakarta.annotation.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/live")
@CrossOrigin
public class LiveClassesController {

    private final LiveClassService service;

    public LiveClassesController(LiveClassService service) {
        this.service = service;
    }

    @GetMapping("/classes")
    public List<LiveClass> list(@RequestParam("courseId") Long courseId) {
        return service.listByCourse(courseId);
    }

    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','MODERATOR')")
    @PostMapping("/classes")
    public LiveClass create(@RequestBody Map<String, Object> body, Principal principal) {
        Long courseId = ((Number) body.get("courseId")).longValue();
        String title = (String) body.getOrDefault("title", "Live class");

        Instant startsAt = null;
        if (body.get("startsAt") != null) {
            try { startsAt = Instant.parse(String.valueOf(body.get("startsAt"))); } catch (Exception ignored) {}
        }
        Integer duration = null;
        if (body.get("durationMinutes") != null) {
            duration = ((Number) body.get("durationMinutes")).intValue();
        }

        return service.create(courseId, title, startsAt, duration, principal.getName());
    }

    @PostMapping("/classes/{id}/join")
    public Map<String, Object> join(@PathVariable("id") Long id, Principal principal) {
        service.join(id, principal.getName());
        return Map.of("status", "joined");
    }

    @PostMapping("/files/upload")
    public LiveClassFile upload(@RequestParam("liveClassId") Long liveClassId,
                                @RequestParam("file") MultipartFile file,
                                Principal principal) throws IOException {
        return service.uploadFile(liveClassId, principal.getName(), file);
    }

    @GetMapping("/files/list")
    public List<LiveClassFile> listFiles(@RequestParam("liveClassId") Long liveClassId) {
        return service.listFiles(liveClassId);
    }
}
