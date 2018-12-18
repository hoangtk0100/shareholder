package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.SharePeriod;

import java.io.Serializable;
import java.util.UUID;

public interface PersonShareDao extends CommonHibernateInterface<Serializable, SharePeriod> {
    // Retrieve person share by id
    SharePeriod retrieveById(UUID id) throws NotFoundException;

    // Retrieve person share by person id
    SharePeriod retrieveByPersonId(UUID personId) throws NotFoundException;

    // Retrieve person share by stage
    SharePeriod retrieveByStage(UUID stageId) throws NotFoundException;

    // Retrieve person share by person - stage
    SharePeriod retrieveByStage(UUID stageId, UUID personId) throws NotFoundException;
}
