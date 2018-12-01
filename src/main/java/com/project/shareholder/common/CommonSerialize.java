package com.project.shareholder.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class CommonSerialize implements Serializable {

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "date_created_at")
    private Timestamp dateCreatedAt;

    @Column(name = "date_updated_at")
    private Timestamp dateUpdatedAt;

    @Column(name = "date_deleted_at")
    private Timestamp dateDeletedAt;

    // Getter and setter methods

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(Timestamp dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }

    public Timestamp getDateUpdatedAt() {
        return dateUpdatedAt;
    }

    public void setDateUpdatedAt(Timestamp dateUpdatedAt) {
        this.dateUpdatedAt = dateUpdatedAt;
    }

    public Timestamp getDateDeletedAt() {
        return dateDeletedAt;
    }

    public void setDateDeletedAt(Timestamp dateDeletedAt) {
        this.dateDeletedAt = dateDeletedAt;
    }
}

