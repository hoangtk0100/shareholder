package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.StageRequest;

import java.util.List;

public interface StageService {
    // Create a new stage
    Stage create(StageRequest stageRequest) throws DatabaseException;

    // Update stage
    Stage update(StageRequest stageRequest) throws DatabaseException;

    // Deactivate stage
    Stage deactivate(StageRequest stageRequest) throws DatabaseException;

    // Delete stage
    Stage delete(StageRequest stageRequest) throws DatabaseException;

    // Retrieve stage by id
    Stage retrieveById(StageRequest stageRequest) throws DatabaseException;

    // Retrieve all stages
    List<Stage> list();
}
