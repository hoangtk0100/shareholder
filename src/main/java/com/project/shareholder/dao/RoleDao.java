package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.Role;

import java.io.Serializable;
import java.util.UUID;

public interface RoleDao extends CommonHibernateInterface<Serializable, Role> {
    // Retrieve role by id
    Role retrieveById(UUID id) throws NotFoundException;

    // Retrieve role by name
    Role retrieveByName(String name) throws NotFoundException;

    // Retrieve role by person id
    Role retrieveByPerson(Person person) throws NotFoundException;
}
