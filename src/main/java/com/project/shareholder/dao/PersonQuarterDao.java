package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.Quarter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface PersonQuarterDao extends CommonHibernateInterface<Serializable, PersonQuarter> {
    // Retrieve person quarter by id
    PersonQuarter retrieveById(UUID id) throws NotFoundException;

    // Retrieve person quarter by quarter
    List<PersonQuarter> retrieveByQuarter(Quarter quarter) throws NotFoundException;

    // Retrieve person quarter by person
    List<PersonQuarter> retrieveByPerson(Person person) throws NotFoundException;

    // Retrieve person quarter by person - stage
    PersonQuarter retrieveByPersonStage(Quarter quarter, Person person) throws NotFoundException;
}
