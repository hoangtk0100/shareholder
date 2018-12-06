package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonShareStage;

import java.io.Serializable;
import java.util.UUID;

public interface PersonShareStageDao extends CommonHibernateInterface<Serializable, PersonShareStage> {
    // Retrieve person share by id
    PersonShareStage retrieveById(UUID id) throws NotFoundException;

    // Retrieve person share by stage id
    PersonShareStage retrieveByStageId(UUID stageId) throws NotFoundException;

    // Retrieve person share by person id
    PersonShareStage retrieveByPersonId(UUID personId) throws NotFoundException;

    // Retrieve person share by person - stage
    PersonShareStage retrieveByPersonStage(UUID stageId, UUID personId) throws NotFoundException;
}
