package com.eduegy.backend.service;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.model.LiveClass;
import com.eduegy.backend.model.LiveClassAttendance;
import com.eduegy.backend.model.LiveClassFile;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.CourseRepository;
import com.eduegy.backend.repository.LiveClassAttendanceRepository;
import com.eduegy.backend.repository.LiveClassFileRepository;
import com.eduegy.backend.repository.LiveClassRepository;
import com.eduegy.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class LiveClassService {

    private final LiveClassRepository liveClassRepo;
    private final LiveClassAttendanceRepository attendanceRepo;
    private final LiveClassFileRepository fileRepo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;
    private final StorageService storage;

    public LiveClassService(LiveClassRepository liveClassRepo,
                            LiveClassAttendanceRepository attendanceRepo,
                            LiveClassFileRepository fileRepo,
                            CourseRepository courseRepo,
                            UserRepository userRepo,
                            StorageService storage) {
        this.liveClassRepo = liveClassRepo;
        this.attendanceRepo = attendanceRepo;
        this.fileRepo = fileRepo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.storage = storage;
    }

    public LiveClass create(Long courseId, String title, Instant startsAt, Integer durationMinutes, String creatorEmail) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        User creator = userRepo.findByEmail(creatorEmail).orElseThrow(() -> new RuntimeException("User not found"));

        LiveClass lc = new LiveClass();
        lc.setCourse(course);
        lc.setTitle(title != null && !title.isBlank() ? title : "Live class");
        lc.setRoomCode("EDU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        lc.setStartsAt(startsAt);
        lc.setDurationMinutes(durationMinutes);
        lc.setCreatedBy(creator);
        lc.setCreatedAt(Instant.now());
        return liveClassRepo.save(lc);
    }

    public List<LiveClass> listByCourse(Long courseId) {
        return liveClassRepo.findByCourse_IdOrderByCreatedAtDesc(courseId);
    }

    public LiveClass get(Long id) {
        return liveClassRepo.findById(id).orElseThrow(() -> new RuntimeException("Live class not found"));
    }

    public void join(Long liveClassId, String email) {
        LiveClass lc = get(liveClassId);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        attendanceRepo.findByLiveClass_IdAndUser_Id(liveClassId, user.getId())
                .orElseGet(() -> attendanceRepo.save(new LiveClassAttendance(lc, user)));
    }

    public LiveClassFile uploadFile(Long liveClassId, String email, MultipartFile file) throws IOException {
        LiveClass lc = get(liveClassId);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        var dir = storage.ensureDir("live", String.valueOf(liveClassId));
        String original = file.getOriginalFilename() == null ? "file" : file.getOriginalFilename();
        String stored = System.currentTimeMillis() + "_" + StorageService.sanitize(original);
        storage.store(file, dir, stored);

        LiveClassFile lcf = new LiveClassFile(lc, user, original, stored);
        return fileRepo.save(lcf);
    }

    public List<LiveClassFile> listFiles(Long liveClassId) {
        get(liveClassId); // validate exists
        return fileRepo.findByLiveClass_IdOrderByUploadedAtDesc(liveClassId);
    }
}
