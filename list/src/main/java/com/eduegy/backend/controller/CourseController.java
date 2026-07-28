package com.eduegy.backend.controller;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAllCourses();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public Course create(@RequestBody Course course) {
        return service.createCourse(course);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public void delete(@PathVariable Long id) {
        service.deleteCourse(id);
    }

}