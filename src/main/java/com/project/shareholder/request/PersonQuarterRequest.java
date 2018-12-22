package com.project.shareholder.request;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonQuarterRequest {
    private UUID id;

    @NotNull(message = "Stock quantity must not be empty")
    private double stockQuantity;

    private double bonusStock;
    private int referralQuantity;
    private ArrayList<UUID> referralIds;

    @NotNull(message = "Person must not be null")
    private UUID personId;

    @NotNull(message = "Quarter must not be null")
    private UUID quarterId;

    private List<UUID> sharePeriodIds;
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

    public double getBonusStock() {
        return bonusStock;
    }

    public void setBonusStock(double bonusStock) {
        this.bonusStock = bonusStock;
    }

    public int getReferralQuantity() {
        return referralQuantity;
    }

    public void setReferralQuantity(int referralQuantity) {
        this.referralQuantity = referralQuantity;
    }

    public ArrayList<UUID> getReferralIds() {
        return referralIds;
    }

    public void setReferralIds(ArrayList<UUID> referralIds) {
        this.referralIds = referralIds;
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

    public List<UUID> getSharePeriodIds() {
        return sharePeriodIds;
    }

    public void setSharePeriodIds(List<UUID> sharePeriodIds) {
        this.sharePeriodIds = sharePeriodIds;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
