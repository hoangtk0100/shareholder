package com.project.shareholder.request;

import com.project.shareholder.model.Person;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ActivityRequest {
    @NotNull(message = "Id must not be empty")
    private UUID id;

    @NotBlank(message = "Person must not be empty")
    private Person person;

    @NotBlank(message = "Type must not be empty")
    private String type;

    @NotBlank(message = "Target must not be empty")
    private String target;

    @NotBlank(message = "Content must not be empty")
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
