package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.PersonRequest;

import java.util.List;

public interface PersonService {
    // Create new person
    Person create(PersonRequest personRequest) throws DatabaseException, NotFoundException;

    // Update person information
    Person update(PersonRequest personRequest) throws DatabaseException, NotFoundException;

    // Deactivate person
    Boolean deactivate(String id) throws DatabaseException;

    // Delete person
    Boolean delete(String id) throws DatabaseException;

    // Retrieve person by id
    Person retrieveById(String id) throws NotFoundException;

    // Retrieve person by username
    Person retrieveByUsername(String username) throws NotFoundException;

    // Retrieve person by personal id
    Person retrieveByPersonalId(String personalId) throws NotFoundException;

    // Retrieve person phone number
    Person retrieveByPhoneNumber(String phoneNumber) throws NotFoundException;

    // Retrieve all persons
    List<Person> list();

    // Add referral
    Person addReferral(PersonRequest personRequest) throws DatabaseException;
}
