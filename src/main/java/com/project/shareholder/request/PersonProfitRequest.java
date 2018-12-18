package com.project.shareholder.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PersonProfitRequest {
    private UUID id;

    @NotNull(message = "Person must be identified")
    private UUID personId;

    @NotNull(message = "Period must be identified")
    private UUID profitId;

    @NotNull(message = "Profit must not be empty")
    private double periodProfit;

    // Getter and setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public UUID getProfitId() {
        return profitId;
    }

    public void setProfitId(UUID profitId) {
        this.profitId = profitId;
    }

    public double getPeriodProfit() {
        return periodProfit;
    }

    public void setPeriodProfit(double periodProfit) {
        this.periodProfit = periodProfit;
    }
}
