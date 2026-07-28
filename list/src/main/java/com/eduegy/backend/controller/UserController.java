package com.eduegy.backend.controller;

import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.UserRepository;
import com.eduegy.backend.dto.UserPublicDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    
    @GetMapping("/all")
    public List<UserPublicDto> allUsers() {
        return repo.findAll().stream()
                .map(u -> new UserPublicDto(u.getId(), u.getFullName(), u.getEmail(), u.getRole().name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}
