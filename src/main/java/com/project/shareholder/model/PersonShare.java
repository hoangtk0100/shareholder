package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "person_share",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id"}),
                             @UniqueConstraint(columnNames = {"person_id", "stage_id"})
        }
)

public class PersonShare extends CommonSerialize {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "stock_quantity")
    private double stockQuantity;

    @Column(name = "note")
    private String note;

    @NotNull
    @Column(name = "period")
    private Timestamp period;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private PersonShareStage personShareStage;

    // Getter and setter methods
    public String getId() {
        return id.toString();
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

    public PersonShareStage getPersonShareStage() {
        return personShareStage;
    }

    public void setPersonShareStage(PersonShareStage personShareStage) {
        this.personShareStage = personShareStage;
    }
}
