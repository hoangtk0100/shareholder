package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;

import java.io.Serializable;
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

    // Retrieve person total shares
    double retrieveTotalStock(UUID id) throws NotFoundException;

    // Update total shares
    void updateTotalStock(UUID id, double stockQuantity) throws DatabaseException;
}
