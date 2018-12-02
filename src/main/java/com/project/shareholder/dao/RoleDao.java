package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Role;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.Serializable;
import java.util.UUID;

public interface RoleDao extends CommonHibernateInterface<Serializable, Role> {
    // Retrieve role by id
    Role retrieveById(UUID id) throws DatabaseException;

    // Retrieve role by name
    Role retrieveByName(String name) throws DatabaseException;
}
