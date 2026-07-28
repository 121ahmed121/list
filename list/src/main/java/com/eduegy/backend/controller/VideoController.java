package com.eduegy.backend.controller;

import com.eduegy.backend.model.Video;
import com.eduegy.backend.service.VideoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/course/{courseId}")
    public List<Video> getByCourse(@PathVariable Long courseId) {
        return videoService.getByCourse(courseId);
    }
}
