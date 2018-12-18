package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Profit;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Date;
import java.util.UUID;

public interface ProfitDao extends CommonHibernateInterface<Serializable, Profit> {
    // Retrieve profit by id
    Profit retrieveById(UUID id) throws NotFoundException;

    // Retrieve profit by period
    Profit retrieveByPeriod(YearMonth period) throws NotFoundException;
}
