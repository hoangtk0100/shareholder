package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.request.PersonProfitRequest;

import java.util.List;
import java.util.UUID;

public interface PersonProfitService {
    // Create person profit
    PersonProfit create(PersonProfitRequest personProfitRequest) throws DatabaseException;

    // Retrieve person profit by id
    PersonProfit retrieveById(String id) throws NotFoundException;

    // Retrieve person profit by person id
    List<PersonProfit> retrieveByPersonId(String personId) throws NotFoundException;

    // Retrieve person profit by profit id
    List<PersonProfit> retrieveByProfitId(String profitId) throws NotFoundException;

    // Retrieve person profit by person profit
    PersonProfit retrieveByPersonProfit(String personId, String profitId) throws NotFoundException;

    List<PersonProfit> list();
}
