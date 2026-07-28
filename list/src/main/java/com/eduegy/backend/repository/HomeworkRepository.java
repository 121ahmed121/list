package com.eduegy.backend.repository;

import com.eduegy.backend.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findByCourse_IdOrderByCreatedAtDesc(Long courseId);
}
