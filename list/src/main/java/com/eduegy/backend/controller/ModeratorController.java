package com.eduegy.backend.controller;

import com.eduegy.backend.model.Role;
import com.eduegy.backend.model.User;
import com.eduegy.backend.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderator")
@CrossOrigin
public class ModeratorController {

    private final UserRepository userRepository;

    public ModeratorController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<User> users() {
        return userRepository.findAll();
    }

    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public User setRole(@PathVariable Long id, @RequestParam String role) {
        User u = userRepository.findById(id).orElseThrow();
        Role r = Role.valueOf(role.trim().toUpperCase());
        // moderator can't assign ADMIN
        if (u.getRole() == Role.ADMIN) return u;
        if (r == Role.ADMIN) return u;
        u.setRole(r);
        return userRepository.save(u);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public void delete(@PathVariable Long id) {
        User u = userRepository.findById(id).orElseThrow();
        if (u.getRole() == Role.ADMIN) return;
        userRepository.deleteById(id);
    }
}
