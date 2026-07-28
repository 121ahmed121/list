package com.eduegy.backend.repository;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.model.Progress;
import com.eduegy.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByUser(User user);

    Optional<Progress> findByUserAndCourse(User user, Course course);
}
