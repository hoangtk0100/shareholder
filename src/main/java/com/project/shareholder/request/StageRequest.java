package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertDate;

public class StageRequest {
    private UUID id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Stock quantity must not be empty")
    private double quantity;

    @NotBlank(message = "Start date must not be empty")
    private String dateStartedAt;

    @NotBlank(message = "End date must not be empty")
    private String dateEndedAt;

    private String note;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getDateStartedAt() {
        return convertDate(dateStartedAt);
    }

    public void setDateStartedAt(String dateStartedAt) {
        this.dateStartedAt = dateStartedAt;
    }

    public Date getDateEndedAt() {
        return convertDate(dateEndedAt);
    }

    public void setDateEndedAt(String dateEndedAt) {
        this.dateEndedAt = dateEndedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
