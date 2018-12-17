package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.UUID;

public interface StageDao extends CommonHibernateInterface<Serializable, Stage> {
    // Retrieve stage by id
    Stage retrieveById(UUID id) throws NotFoundException;

    // Retrieve stage by name
    Stage retrieveByName(String name) throws NotFoundException;

    // Retrieve stage by period
    Stage retrieveByPeriod(YearMonth period) throws NotFoundException;
}
