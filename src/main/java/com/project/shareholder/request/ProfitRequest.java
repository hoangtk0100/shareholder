package com.project.shareholder.request;

import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;
import java.util.UUID;

public class ProfitRequest {
    private UUID id;

    @NotBlank(message = "Profit must not be empty")
    private double totalProfit;

    @NotBlank(message = "Period must not be empty")
    private Timestamp period;

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

    public Timestamp getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }
}
