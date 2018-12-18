package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Quarter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public interface QuarterDao extends CommonHibernateInterface<Serializable, Quarter> {
    // Retrieve quarter by id
    Quarter retrieveById(UUID id) throws NotFoundException;

    // Retrieve quarter by name
    Quarter retrieveByName(String name) throws NotFoundException;

    // Retrieve quarter by period
    Quarter retrieveByPeriod(Date period) throws NotFoundException;
}
