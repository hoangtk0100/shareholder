package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonShare;

import java.io.Serializable;
import java.util.UUID;

public interface PersonShareDao extends CommonHibernateInterface<Serializable, PersonShare> {
    // Retrieve person share by id
    PersonShare retrieveById(UUID id) throws NotFoundException;

    // Retrieve person share by person id
    PersonShare retrieveByPersonId(UUID personId) throws NotFoundException;

    // Retrieve person share by stage
    PersonShare retrieveByStage(UUID stageId) throws NotFoundException;

    // Retrieve person share by person - stage
    PersonShare retrieveByStage(UUID stageId, UUID personId) throws NotFoundException;
}
