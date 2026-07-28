package com.eduegy.backend.service;

import com.eduegy.backend.model.Video;
import com.eduegy.backend.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public List<Video> getByCourse(Long courseId) {
        return videoRepository.findByCourseId(courseId);
    }
}
