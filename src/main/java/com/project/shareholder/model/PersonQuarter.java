package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person_quarter",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id", "quarter_id"}),
                             @UniqueConstraint(columnNames = {"id"}),
                             @UniqueConstraint(columnNames = {"period"})
        }
)

public class PersonQuarter extends CommonSerialize {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "stock_quantity")
    private double stockQuantity;

    @NotNull
    @Column(name = "bonus_stock")
    private double bonusStock;

    @NotNull
    @Column(name = "referral_quantity")
    private int referralQuantity;

    @Column(name = "referral_ids")
    private List<UUID> referralIds;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quarter_id", referencedColumnName = "id")
    private Quarter quarter;

    @OneToMany(mappedBy = "personQuarter", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SharePeriod> sharePeriods;

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

    public List<UUID> getReferralIds() {
        return referralIds;
    }

    public void setReferralIds(List<UUID> referralIds) {
        this.referralIds = referralIds;
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

    public Quarter getQuarter() {
        return quarter;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    public List<SharePeriod> getSharePeriods() {
        return sharePeriods;
    }

    public void setSharePeriods(List<SharePeriod> sharePeriods) {
        this.sharePeriods = sharePeriods;
    }
}
