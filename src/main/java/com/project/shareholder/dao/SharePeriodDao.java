package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.SharePeriod;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface SharePeriodDao extends CommonHibernateInterface<Serializable, SharePeriod> {
    // Retrieve person share by id
    SharePeriod retrieveById(UUID id) throws NotFoundException;

    // Retrieve person share by person - quarter
    List<SharePeriod> retrieveByPersonQuarter(PersonQuarter personQuarter) throws NotFoundException;
}
