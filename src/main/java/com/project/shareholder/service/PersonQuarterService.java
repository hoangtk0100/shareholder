package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.request.PersonQuarterRequest;

import java.util.List;

public interface PersonQuarterService {
    // Create person quarter
    PersonQuarter create(PersonQuarterRequest personQuarterRequest) throws DatabaseException;

    // Update person quarter
    PersonQuarter update(PersonQuarterRequest personQuarterRequest) throws DatabaseException;

    // Deactivate person quarter
    PersonQuarter deactivate(String id) throws DatabaseException;

    // Retrieve person quarter by id
    PersonQuarter retrieveById(String id) throws NotFoundException;

    // Retrieve person quarter by person id
    List<PersonQuarter> retrieveByPersonId(String personId) throws NotFoundException;

    // Retrieve person quarter by quarter id
    List<PersonQuarter> retrieveByQuarterId(String quarterId) throws NotFoundException;

    // Retrieve person quarter by person quarter
    PersonQuarter retrieveByPersonQuarter(String personId, String quarterId) throws NotFoundException;

    // Retrieve person quarter by person period
    PersonQuarter retrieveByPersonPeriod(String personId, String period) throws NotFoundException;

    // Retrieve all PersonQuarters
    List<PersonQuarter> list();
}
