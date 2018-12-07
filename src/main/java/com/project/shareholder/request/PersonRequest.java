package com.project.shareholder.request;

import com.project.shareholder.model.Role;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.UUID;

public class PersonRequest {
    @NotBlank(message = "Id must not be null")
    private UUID id;

    @NotBlank(message = "Id must not be null")
    private String username;

    @NotBlank(message = "Password must not be null")
    private String password;

    @NotBlank(message = "Permission must not be null")
    private Role role;

    private String personalId;

    @NotBlank(message = "Full name must not be empty")
    private String fullName;

    private String phoneNumber;
    private boolean gender;
    private Date birthday;
    private String address;

    @NotBlank(message = "Email must not be empty")
    private String email;

    private String avatar;
    private String referralUsername;

    // Getter and setter methods
    public String getId() {
        return id.toString();
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getReferralUsername() {
        return referralUsername;
    }

    public void setReferralUsername(String referralUsername) {
        this.referralUsername = referralUsername;
    }
}
