package com.project.shareholder.request;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ProfitRequest {
    private UUID id;

    @NotNull(message = "Profit must not be empty")
    private double totalProfit;

    @NotNull(message = "Period must not be empty")
    private String period;

    // Getter and setter methods
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
