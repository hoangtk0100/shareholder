package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "person_period",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}),
                             @UniqueConstraint(columnNames = {"period"})
        }
)

public class SharePeriod extends CommonSerialize {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "stock_quantity")
    private double stockQuantity;

    @NotNull
    @Column(name = "period", unique = true)
    private Timestamp period;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_quarter_id", referencedColumnName = "id")
    private PersonQuarter personQuarter;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PersonQuarter getPersonQuarter() {
        return personQuarter;
    }

    public void setPersonQuarter(PersonQuarter personQuarter) {
        this.personQuarter = personQuarter;
    }
}
