package com.eduegy.backend.service;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.model.Homework;
import com.eduegy.backend.model.HomeworkSubmission;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.CourseRepository;
import com.eduegy.backend.repository.HomeworkRepository;
import com.eduegy.backend.repository.HomeworkSubmissionRepository;
import com.eduegy.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class HomeworkService {

    private final HomeworkRepository hwRepo;
    private final HomeworkSubmissionRepository subRepo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;
    private final StorageService storage;

    public HomeworkService(HomeworkRepository hwRepo,
                           HomeworkSubmissionRepository subRepo,
                           CourseRepository courseRepo,
                           UserRepository userRepo,
                           StorageService storage) {
        this.hwRepo = hwRepo;
        this.subRepo = subRepo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.storage = storage;
    }

    public Homework create(Long courseId, String title, String description, Instant dueAt, String teacherEmail) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        User teacher = userRepo.findByEmail(teacherEmail).orElseThrow(() -> new RuntimeException("User not found"));

        Homework hw = new Homework();
        hw.setCourse(course);
        hw.setTitle(title != null && !title.isBlank() ? title : "Homework");
        hw.setDescription(description);
        hw.setDueAt(dueAt);
        hw.setCreatedBy(teacher);
        hw.setCreatedAt(Instant.now());
        return hwRepo.save(hw);
    }

    public List<Homework> listByCourse(Long courseId) {
        return hwRepo.findByCourse_IdOrderByCreatedAtDesc(courseId);
    }

    public Homework get(Long id) {
        return hwRepo.findById(id).orElseThrow(() -> new RuntimeException("Homework not found"));
    }

    public HomeworkSubmission submit(Long homeworkId, String studentEmail, MultipartFile file) throws IOException {
        Homework hw = get(homeworkId);
        User student = userRepo.findByEmail(studentEmail).orElseThrow(() -> new RuntimeException("User not found"));

        var dir = storage.ensureDir("homework", String.valueOf(homeworkId), String.valueOf(student.getId()));
        String original = file.getOriginalFilename() == null ? "file" : file.getOriginalFilename();
        String stored = System.currentTimeMillis() + "_" + StorageService.sanitize(original);
        storage.store(file, dir, stored);

        HomeworkSubmission sub = new HomeworkSubmission(hw, student, original, stored);
        return subRepo.save(sub);
    }

    public List<HomeworkSubmission> listSubmissions(Long homeworkId) {
        get(homeworkId);
        return subRepo.findByHomework_IdOrderBySubmittedAtDesc(homeworkId);
    }

    public List<HomeworkSubmission> mySubmissions(String studentEmail) {
        User student = userRepo.findByEmail(studentEmail).orElseThrow(() -> new RuntimeException("User not found"));
        return subRepo.findByStudent_IdOrderBySubmittedAtDesc(student.getId());
    }
}
