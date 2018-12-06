package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;

import java.io.Serializable;
import java.util.UUID;

public interface ActivityDao extends CommonHibernateInterface<Serializable, Activity> {
    // Retrieve activity by id
    Activity retrieveById(UUID id) throws NotFoundException;

    // Retrieve activity by person id
    Activity retrieveByPersonId(UUID personId) throws NotFoundException;
}
