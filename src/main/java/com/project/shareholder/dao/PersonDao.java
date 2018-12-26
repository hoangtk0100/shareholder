package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface PersonDao extends CommonHibernateInterface<Serializable, Person> {
    // Retrieve person by id
    Person retrieveById(UUID id) throws NotFoundException;

    // Retrieve person by phone number
    Person retrieveByPhoneNumber(String phoneNumber) throws NotFoundException;

    // Retrieve person by username
    Person retrieveByUsername(String username) throws NotFoundException;

    // Retrieve person by personal id
    Person retrieveByPersonalId(String personalId) throws NotFoundException;

    // Retrieve all total stock
    double retrieveAllTotalStock();

    // Retrieve all active person
    List<Person> retrieveActivePersons();
}
