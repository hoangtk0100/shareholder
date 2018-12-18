package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.model.Person;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface ActivityDao extends CommonHibernateInterface<Serializable, Activity> {
    // Retrieve activity by id
    Activity retrieveById(UUID id) throws NotFoundException;

    // Retrieve activity by person id
    List<Activity> retrieveByPerson(Person person) throws NotFoundException;
}
