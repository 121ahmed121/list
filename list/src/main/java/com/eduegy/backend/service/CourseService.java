package com.eduegy.backend.service;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course createCourse(Course course) {
        return repository.save(course);
    }


    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }
}
