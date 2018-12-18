package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class RoleRequest {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    private Boolean active;

    // Getter and setter methods
    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
