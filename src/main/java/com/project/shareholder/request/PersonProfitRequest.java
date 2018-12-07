package com.project.shareholder.request;

import com.project.shareholder.model.Person;
import com.project.shareholder.model.Profit;
import org.hibernate.validator.constraints.NotBlank;

import java.util.UUID;

public class PersonProfitRequest {
    private UUID id;

    @NotBlank(message = "Person must be identified")
    private Person person;

    @NotBlank(message = "Profit must be identified")
    private Profit profit;

    @NotBlank(message = "Profit must not be empty")
    private double periodProfit;

    // Getter and setter methods
    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Profit getProfit() {
        return profit;
    }

    public void setProfit(Profit profit) {
        this.profit = profit;
    }

    public double getPeriodProfit() {
        return periodProfit;
    }

    public void setPeriodProfit(double periodProfit) {
        this.periodProfit = periodProfit;
    }
}
