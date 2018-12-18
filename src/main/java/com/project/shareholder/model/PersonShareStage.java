package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_share_stage",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id", "stage_id"}),
                             @UniqueConstraint(columnNames = {"id"})
        }
)

public class PersonShareStage extends CommonSerialize {

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

    @NotNull
    @Column(name = "date_started_at")
    private Timestamp dateStartedAt;

    @NotNull
    @Column(name = "date_ended_at")
    private Timestamp dateEndedAt;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;

    @OneToMany(mappedBy = "personShareStage", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PersonShare> personShares;

    // Getter and setter methods
    public String getId() {
        return id.toString();
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

    public Timestamp getDateStartedAt() {
        return dateStartedAt;
    }

    public void setDateStartedAt(Timestamp dateStartedAt) {
        this.dateStartedAt = dateStartedAt;
    }

    public Timestamp getDateEndedAt() {
        return dateEndedAt;
    }

    public void setDateEndedAt(Timestamp dateEndedAt) {
        this.dateEndedAt = dateEndedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public List<PersonShare> getPersonShares() {
        return personShares;
    }

    public void setPersonShares(List<PersonShare> personShares) {
        this.personShares = personShares;
    }
}
