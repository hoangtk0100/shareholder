package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.request.PersonProfitRequest;

import java.time.YearMonth;
import java.util.UUID;

public interface PersonProfitService {
    // Create person profit
    PersonProfit create(PersonProfitRequest personProfitRequest) throws DatabaseException;

    // Retrieve person profit by id
    PersonProfit retrieveById(UUID id) throws NotFoundException;

    // Retrieve person profit by person id
    PersonProfit retrieveByPersonId(UUID id) throws NotFoundException;

    // Retrieve person profit by profit id
    PersonProfit retrieveByProfitId(UUID id) throws NotFoundException;

    // Retrieve person profit by period
    PersonProfit retrieveByPeriod(YearMonth period) throws NotFoundException;

    // Retrieve person profit by person profit
    PersonProfit retrieveByPersonProfit(UUID personId, UUID profitId) throws NotFoundException;
}
