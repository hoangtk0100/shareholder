package com.project.shareholder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "activity",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})
        }
)

public class Activity extends CommonSerialize {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "target")
    private String target;

    @NotNull
    @Column(name = "content")
    private String content;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
