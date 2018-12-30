package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.ActivityRequest;
import com.project.shareholder.request.PersonRequest;
import com.project.shareholder.service.ActivityService;
import com.project.shareholder.service.PersonService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ActivityService activityService;

    // Create a new person
    @PostMapping("/create")
    public Person create(@Valid @RequestBody PersonRequest personRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        // Create new person
        Person person = personService.create(personRequest);

        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Create a new person with username is " + person.getUsername());
        activityRequest.setPersonId(personRequest.getCreatorId());
        activityRequest.setTarget("Person");
        activityRequest.setType("Create");
        activityService.create(activityRequest);

        return person;
    }

    // Update person profile
    @PutMapping("/update")
    public Person update(@Valid @RequestBody PersonRequest personRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        // Update person information
        Person person = personService.update(personRequest);

        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Update person with username has " + person.getUsername());
        activityRequest.setPersonId(personRequest.getCreatorId());
        activityRequest.setTarget("Person");
        activityRequest.setType("Update");
        activityService.create(activityRequest);

        return person;
    }

    // Deactivate person
    @PutMapping("/deactivate/{id}/{creatorId}")
    public Person deactivate(@PathVariable("id") String id, @PathVariable("creatorId") String creatorId) throws DatabaseException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Deactivate person has id: " + id);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Deactivate");
        activityService.create(activityRequest);

        return personService.deactivate(id);
    }

    // Delete person
    @DeleteMapping("/delete/{id}/{creatorId}")
    public String delete(@PathVariable("id") String id, @PathVariable("creatorId") String creatorId) throws DatabaseException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Delete person has id: " + id);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Delete");
        activityService.create(activityRequest);

        return personService.delete(id);
    }

    // Retrieve all persons
    @GetMapping("/all/{creatorId}")
    public List<Person> retrieveAll(@PathVariable String creatorId) {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Retrieve all persons");
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Retrieve");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return personService.list();
    }

    // Retrieve specific person by id
    @GetMapping("/showById/{id}/{creatorId}")
    public Person retrieveById(@PathVariable("id") String id, @PathVariable("creatorId") String creatorId) throws NotFoundException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Retrieve person has id: " + id);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Retrieve");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return personService.retrieveById(id);
    }

    // Retrieve specific person by username
    @GetMapping("/showByUsername/{username}/{creatorId}")
    public Person retrieveByUsername(@PathVariable("username") String username, @PathVariable("creatorId") String creatorId) throws NotFoundException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Retrieve person has username: " + username);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Retrieve");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return personService.retrieveByUsername(username);
    }

    // Retrieve specific person by personal id
    @GetMapping("/showByPersonalId/{personalId}/{creatorId}")
    public Person retrieveByPersonalId(@PathVariable("personalId") String personalId, @PathVariable("creatorId") String creatorId) throws NotFoundException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Retrieve person has personal id: " + personalId);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Retrieve");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return personService.retrieveByPersonalId(personalId);
    }

    // Retrieve specific person by phone number
    @GetMapping("/showByPhoneNumber/{phoneNumber}/{creatorId}")
    public Person retrieveByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber, @PathVariable("creatorId") String creatorId) throws NotFoundException {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Retrieve person has phone number: " + phoneNumber);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Retrieve");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return personService.retrieveByPhoneNumber(phoneNumber);
    }

    // Update total stock
    @PutMapping("/updateTotalStock/{id}/{stockQuantity}/{creatorId}")
    public void updateTotalStock(@PathVariable("id") String id, @PathVariable("stockQuantity") double stockQuantity, @PathVariable("creatorId") String creatorId) throws DatabaseException, Throwable {
        // Create creator's activity
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setContent("Update person's total stock has id: " + id);
        activityRequest.setPersonId(UUID.fromString(creatorId));
        activityRequest.setTarget("Person");
        activityRequest.setType("Update");
        try {
            activityService.create(activityRequest);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        personService.updateTotalStock(id, stockQuantity);
    }
}
