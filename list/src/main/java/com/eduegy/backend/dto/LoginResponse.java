package com.eduegy.backend.dto;

import com.eduegy.backend.model.Role;

public class LoginResponse {

    private String token;
    private Long id;
    private String email;
    private String fullName;
    private Role role;

    public LoginResponse(String token, Long id, String email, String fullName, Role role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    public String getToken() { return token; }
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
}
