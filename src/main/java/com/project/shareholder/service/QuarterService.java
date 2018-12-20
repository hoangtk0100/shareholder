package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.request.QuarterRequest;

import java.util.List;

public interface QuarterService {
    // Create a new quarter
    Quarter create(QuarterRequest quarterRequest) throws DatabaseException;

    // Update quarter
    Quarter update(QuarterRequest quarterRequest) throws DatabaseException;

    // Deactivate quarter
    Quarter deactivate(String id) throws DatabaseException;

    // Delete quarter
    String delete(String id) throws DatabaseException;

    // Retrieve quarter by id
    Quarter retrieveById(String id) throws NotFoundException;

    // Retrieve quarter by period
    Quarter retrieveByPeriod(String period) throws NotFoundException;

    // Retrieve all quarters
    List<Quarter> list();
}
