package com.eduegy.backend.repository;

import com.eduegy.backend.model.LiveClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveClassRepository extends JpaRepository<LiveClass, Long> {
    List<LiveClass> findByCourse_IdOrderByCreatedAtDesc(Long courseId);
}
