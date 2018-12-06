package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonProfit;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.UUID;

public interface PersonProfitDao extends CommonHibernateInterface<Serializable, PersonProfit> {
    // Retrieve person's profit by id
    PersonProfit retrieveById(UUID id) throws NotFoundException;

    // Retrieve person's profit by person id
    PersonProfit retrieveByPersonId(UUID personId) throws NotFoundException;

    // Retrieve person's profit by period
    PersonProfit retrieveByPeriod(YearMonth period) throws NotFoundException;

    // Retrieve person's profit by person - period
    PersonProfit retrieveByPersonPeriod(UUID personId, YearMonth period) throws NotFoundException;
}
