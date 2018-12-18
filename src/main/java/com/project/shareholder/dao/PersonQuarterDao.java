package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonQuarter;

import java.io.Serializable;
import java.util.UUID;

public interface PersonQuarterDao extends CommonHibernateInterface<Serializable, PersonQuarter> {
    // Retrieve person share by id
    PersonQuarter retrieveById(UUID id) throws NotFoundException;

    // Retrieve person share by stage id
    PersonQuarter retrieveByStageId(UUID stageId) throws NotFoundException;

    // Retrieve person share by person id
    PersonQuarter retrieveByPersonId(UUID personId) throws NotFoundException;

    // Retrieve person share by person - stage
    PersonQuarter retrieveByPersonStage(UUID stageId, UUID personId) throws NotFoundException;
}
