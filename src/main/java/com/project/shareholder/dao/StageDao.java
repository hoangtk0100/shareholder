package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Stage;

import java.io.Serializable;
import java.util.UUID;

public interface StageDao extends CommonHibernateInterface<Serializable, Stage> {
    // Retrieve stage by id
    Stage retrieveById(UUID id) throws DatabaseException;

    // Retrieve stage by name
    Stage retrieveByName(String name) throws DatabaseException;
}
