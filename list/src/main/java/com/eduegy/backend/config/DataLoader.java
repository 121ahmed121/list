package com.eduegy.backend.config;

import com.eduegy.backend.model.Course;
import com.eduegy.backend.model.Role;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.CourseRepository;
import com.eduegy.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(CourseRepository courseRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            // Seed courses
            if (courseRepo.count() == 0) {
                courseRepo.save(new Course("Mathematics", "Learn algebra and geometry", "Science"));
                courseRepo.save(new Course("Physics", "Understand the universe", "Science"));
                courseRepo.save(new Course("Programming", "Java, Web, Spring Boot", "Technology"));
            }

            // Seed admin (can control everything)
            userRepo.findByEmail("admin@eduegy.com").orElseGet(() -> {
                User u = new User();
                u.setFullName("Admin");
                u.setEmail("admin@eduegy.com");
                u.setPassword(encoder.encode("admin123"));
                u.setRole(Role.ADMIN);
                return userRepo.save(u);
            });

            // Seed moderator (can manage subjects/users)
            userRepo.findByEmail("moderator@eduegy.com").orElseGet(() -> {
                User u = new User();
                u.setFullName("Moderator");
                u.setEmail("moderator@eduegy.com");
                u.setPassword(encoder.encode("moderator123"));
                u.setRole(Role.MODERATOR);
                return userRepo.save(u);
            });
        };
    }
}
