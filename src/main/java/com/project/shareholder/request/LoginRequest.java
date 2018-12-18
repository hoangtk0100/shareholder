package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class LoginRequest {
    private UUID id;

    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotBlank(message = "Password must not be empty")
    private String password;

//    @NotBlank(message = "Full name must not be empty")
    private String fullName;

//    @NotBlank(message = "Email must not be empty")
    private String email;

//    @NotBlank(message = "Avatar must not be empty")
    private String avatar;

    // Getter and setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
