package com.eduegy.backend.repository;

import com.eduegy.backend.model.HomeworkSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkSubmissionRepository extends JpaRepository<HomeworkSubmission, Long> {
    List<HomeworkSubmission> findByHomework_IdOrderBySubmittedAtDesc(Long homeworkId);
    List<HomeworkSubmission> findByStudent_IdOrderBySubmittedAtDesc(Long studentId);
}
