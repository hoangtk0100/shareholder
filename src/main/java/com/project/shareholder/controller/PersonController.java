package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.PersonRequest;
import com.project.shareholder.service.PersonService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // Create a new person
    @PostMapping("/create")
    public Person create(@Valid @RequestBody PersonRequest personRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        // Create person
        return personService.create(personRequest);
    }

    // Update person profile
    @PutMapping("/update")
    public Person update(@Valid @RequestBody PersonRequest personRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return personService.update(personRequest);
    }

    // Deactivate person
    @PutMapping("/deactivate/{id}")
    public Person deactivate(@PathVariable String id) throws DatabaseException {
        return personService.deactivate(id);
    }

    // Delete person
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws DatabaseException {
        return personService.delete(id);
    }

    // Retrieve all persons
    @GetMapping("/all")
    public List<Person> retrieveAll() {
        return personService.list();
    }

    // Retrieve specific person by id
    @GetMapping("/showById/{id}")
    public Person retrieveById(@PathVariable String id) throws NotFoundException {
        return personService.retrieveById(id);
    }

    // Retrieve specific person by username
    @GetMapping("/showByUsername/{username}")
    public Person retrieveByUsername(@PathVariable String username) throws NotFoundException {
        return personService.retrieveByUsername(username);
    }

    // Retrieve specific person by personal id
    @GetMapping("/showByPersonalId/{personalId}")
    public Person retrieveByPersonalId(@PathVariable String personalId) throws NotFoundException {
        return personService.retrieveByPersonalId(personalId);
    }

    // Retrieve specific person by phone number
    @GetMapping("/showByPhoneNumber/{phoneNumber}")
    public Person retrieveByPhoneNumber(@PathVariable String phoneNumber) throws NotFoundException {
        return personService.retrieveByPhoneNumber(phoneNumber);
    }

    // Update total stock
    @PutMapping("/updateTotalStock/{id}/{stockQuantity}")
    public void updateTotalStock(@PathVariable("id") String id, @PathVariable("stockQuantity") double stockQuantity) throws DatabaseException, Throwable {
        personService.updateTotalStock(id, stockQuantity);
    }
}
