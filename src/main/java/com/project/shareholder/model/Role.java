package com.project.shareholder.model;

import com.project.shareholder.common.CommonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "role",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})
        }
)

public class Role extends CommonSerialize {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id_generator", strategy = "com.project.shareholder.util.UUIDGenerator")
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    private UUID id;

    @NotNull
    @Column(name = "name")
    private String name;

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
}
