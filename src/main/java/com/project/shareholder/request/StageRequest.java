package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class StageRequest {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Stock quantity must not be empty")
    private double stockQuantity;

    @NotBlank(message = "Start date must not be empty")
    private String dateStartedAt;

    @NotBlank(message = "End date must not be empty")
    private String dateEndedAt;

    private List<UUID> quarterIds;
    private String note;

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

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDateStartedAt() {
        return dateStartedAt;
    }

    public void setDateStartedAt(String dateStartedAt) {
        this.dateStartedAt = dateStartedAt;
    }

    public String getDateEndedAt() {
        return dateEndedAt;
    }

    public void setDateEndedAt(String dateEndedAt) {
        this.dateEndedAt = dateEndedAt;
    }

    public List<UUID> getQuarterIds() {
        return quarterIds;
    }

    public void setQuarterIds(List<UUID> quarterIds) {
        this.quarterIds = quarterIds;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
