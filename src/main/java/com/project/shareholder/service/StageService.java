package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.StageRequest;

import java.util.List;

public interface StageService {
    // Create a new stage
    Stage create(StageRequest stageRequest) throws DatabaseException;

    // Update stage
    Stage update(StageRequest stageRequest) throws DatabaseException;

    // Deactivate stage
    Stage deactivate(String id) throws DatabaseException;

    // Delete stage
    String delete(String id) throws DatabaseException;

    // Retrieve stage by id
    Stage retrieveById(String id) throws NotFoundException;

    // Retrieve stage by period
    Stage retrieveByPeriod(String period) throws NotFoundException;

    // Retrieve all stages
    List<Stage> list();
}
