package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.request.ActivityRequest;

import java.util.List;

public interface ActivityService {
    // Create new activity
    Activity create(ActivityRequest activityRequest) throws DatabaseException;

    // Retrieve activity by id
    Activity retrieveById(String id) throws NotFoundException;

    // Retrieve person's activity by person id
    List<Activity> retrieveByPersonId(String personId) throws NotFoundException;

    // Retrieve all activities
    List<Activity> list();
}
