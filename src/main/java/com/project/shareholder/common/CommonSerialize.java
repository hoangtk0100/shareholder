package com.project.shareholder.common;

import com.project.shareholder.util.Utility;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class CommonSerialize implements Serializable {

    // Columns
    @Column(name = "id", length = Utility.UID_LENGTH, unique = true, nullable = false)
    private UUID id;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "date_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreatedAt = new Date();

    @Column(name = "date_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdatedAt = new Date();

    @Column(name = "date_deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeletedAt = new Date();

    // Methods
    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(Date dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }

    public Date getDateUpdatedAt() {
        return dateUpdatedAt;
    }

    public void setDateUpdatedAt(Date dateUpdatedAt) {
        this.dateUpdatedAt = dateUpdatedAt;
    }

    public Date getDateDeletedAt() {
        return dateDeletedAt;
    }

    public void setDateDeletedAt(Date dateDeletedAt) {
        this.dateDeletedAt = dateDeletedAt;
    }
}

