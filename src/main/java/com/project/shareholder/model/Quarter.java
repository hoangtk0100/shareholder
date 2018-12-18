package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quarter",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}),
                             @UniqueConstraint(columnNames = {"stage_id"}),
                             @UniqueConstraint(columnNames = {"date_started_at"})
        }
)

public class Quarter extends CommonSerialize {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "stock_quantity")
    private double stockQuantity;

    @Column(name = "stock_quantity_left")
    private double stockQuantityLeft;

    @NotNull
    @Column(name = "date_started_at", columnDefinition = "DATE")
    private Date dateStartedAt;

    @NotNull
    @Column(name = "date_ended_at", columnDefinition = "DATE")
    private Date dateEndedAt;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;

    @OneToMany(mappedBy = "quarter", fetch = FetchType.EAGER)
    private List<PersonQuarter> personQuarters;

    // Getter and setter methods
    public UUID getId() {
        return id;
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

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getStockQuantityLeft() {
        return stockQuantityLeft;
    }

    public void setStockQuantityLeft(double stockQuantityLeft) {
        this.stockQuantityLeft = stockQuantityLeft;
    }

    public Date getDateStartedAt() {
        return dateStartedAt;
    }

    public void setDateStartedAt(Date dateStartedAt) {
        this.dateStartedAt = dateStartedAt;
    }

    public Date getDateEndedAt() {
        return dateEndedAt;
    }

    public void setDateEndedAt(Date dateEndedAt) {
        this.dateEndedAt = dateEndedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<PersonQuarter> getPersonQuarters() {
        return personQuarters;
    }

    public void setPersonQuarters(List<PersonQuarter> personQuarters) {
        this.personQuarters = personQuarters;
    }
}