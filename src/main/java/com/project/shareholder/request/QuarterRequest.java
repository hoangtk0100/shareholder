package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class QuarterRequest {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Stock quantity must not be empty")
    private double stockQuantity;

    private double stockQuantityLeft = stockQuantity;

    @NotBlank(message = "Start date must not be empty")
    private String dateStartedAt;

    private String dateEndedAt;

    @NotNull(message = "Stage must not be empty")
    private UUID stageId;
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

    public double getStockQuantityLeft() {
        return stockQuantityLeft;
    }

    public void setStockQuantityLeft(double stockQuantityLeft) {
        this.stockQuantityLeft = stockQuantityLeft;
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

    public UUID getStageId() {
        return stageId;
    }

    public void setStageId(UUID stageId) {
        this.stageId = stageId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
