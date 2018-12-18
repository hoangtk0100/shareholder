package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class SharePeriodRequest {
    private UUID id;

    @NotBlank(message = "Stock quantity must not be empty")
    private double stockQuantity;

    @NotBlank(message = "Period must be specific")
    private Timestamp period;

    @NotNull(message = "Person quarter must not be null")
    private UUID personQuarterId;

    private String note;

    // Getter and setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Timestamp getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }

    public UUID getPersonQuarterId() {
        return personQuarterId;
    }

    public void setPersonQuarterId(UUID personQuarterId) {
        this.personQuarterId = personQuarterId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
