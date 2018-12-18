package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class PersonRequest {
    private UUID id;

    @NotBlank(message = "Username must not be null")
    private String username;

    @NotBlank(message = "Password must not be null")
    private String password;

    @NotNull(message = "Permission must not be null")
    private UUID roleId;

    private String personalId;

    @NotBlank(message = "Full name must not be empty")
    private String fullName;

    @NotBlank(message = "Phone number must not be empty")
    private String phoneNumber;
    private boolean gender;
    private String birthday;
    private String address;

    @NotBlank(message = "Email must not be empty")
    private String email;

    private String avatar;
    private double totalStock;
    private double totalProfit;
    private UUID referrerId;
    private List<UUID> personProfitIds;
    private List<UUID> personQuarterIds;

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

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public UUID getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(UUID referrerId) {
        this.referrerId = referrerId;
    }

    public List<UUID> getPersonProfitIds() {
        return personProfitIds;
    }

    public void setPersonProfitIds(List<UUID> personProfitIds) {
        this.personProfitIds = personProfitIds;
    }

    public List<UUID> getPersonQuarterIds() {
        return personQuarterIds;
    }

    public void setPersonQuarterIds(List<UUID> personQuarterIds) {
        this.personQuarterIds = personQuarterIds;
    }
}
