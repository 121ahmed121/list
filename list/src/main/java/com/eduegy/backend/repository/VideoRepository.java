package com.eduegy.backend.repository;

import com.eduegy.backend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByCourseId(Long courseId);
}
