package com.project.shareholder.request;

import com.project.shareholder.model.ShareAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class SharePeriodRequest {
    private UUID id;

    @NotNull(message = "Stock quantity must not be empty")
    private double stockQuantity;

    @NotNull(message = "Period must be specific")
    private Timestamp period;

    @NotNull(message = "Person quarter must not be null")
    private UUID personQuarterId;

    private UUID personId;
    private UUID quarterId;

    private ShareAction shareAction;
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

    public ShareAction getShareAction() {
        return shareAction;
    }

    public void setShareAction(ShareAction shareAction) {
        this.shareAction = shareAction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public UUID getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(UUID quarterId) {
        this.quarterId = quarterId;
    }
}
