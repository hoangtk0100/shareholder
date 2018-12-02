package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Profit;

import java.io.Serializable;
import java.util.UUID;

public interface ProfitDao extends CommonHibernateInterface<Serializable, Profit> {
    // Retrieve profit by id
    Profit retrieveById(UUID id) throws DatabaseException;

    // Retrieve profit by
}
