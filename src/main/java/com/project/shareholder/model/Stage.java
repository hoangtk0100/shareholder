package com.project.shareholder.model;

import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stage",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})
        }
)

public class Stage extends CommonSerialize {

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
    private int quantity;

    @NotNull
    @Column(name = "date_started_at")
    private Timestamp dateStartedAt;

    @NotNull
    @Column(name = "date_ended_at")
    private Timestamp dateEndedAt;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "stage", fetch = FetchType.EAGER)
    private List<PersonShareStage> personShareStages;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public List<PersonShareStage> getPersonShareStages() {
        return personShareStages;
    }

    public void setPersonShareStages(List<PersonShareStage> personShareStages) {
        this.personShareStages = personShareStages;
    }
}