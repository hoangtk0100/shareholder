package com.project.shareholder.request;

import com.project.shareholder.model.Person;
import com.project.shareholder.model.Stage;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;
import java.util.UUID;

public class PersonShareRequest {
    private UUID id;

    @NotBlank(message = "Stock quantity must not be empty")
    private double stockQuantity;

    private String note;

    @NotBlank(message = "Period must be specific")
    private Timestamp period;

    @NotBlank(message = "Person must be specific")
    private Person person;

    @NotBlank(message = "Stage must be specific")
    private Stage stage;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
