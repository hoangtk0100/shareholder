package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Profit;
import com.project.shareholder.request.ProfitRequest;

import java.time.YearMonth;
import java.util.List;

public interface ProfitService {
    // Create new profit
    Profit create(ProfitRequest profitRequest) throws DatabaseException;

    // Update profit
    Profit update(ProfitRequest profitRequest) throws DatabaseException;

    // Deactivate profit
    Profit deactivate(String id) throws DatabaseException;

    // Delete profit
    String delete(String id) throws DatabaseException;

    // Retrieve profit by id
    Profit retrieveById(String id) throws NotFoundException;

    // Retrieve profit by period
    Profit retrieveByPeriod(String period) throws NotFoundException;

    // Retrieve all profits
    List<Profit> list();
}
