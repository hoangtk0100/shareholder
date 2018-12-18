package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class RoleRequest {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    // Getter and setter methods
    public UUID getId() {
        return id;
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
}
