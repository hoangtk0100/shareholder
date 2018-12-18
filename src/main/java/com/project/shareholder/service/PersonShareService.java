package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.request.PersonShareRequest;

public interface PersonShareService {
    // Add share
    SharePeriod add(PersonShareRequest personShareRequest) throws DatabaseException;

    // Subtract share
    SharePeriod subtract(PersonShareRequest personShareRequest) throws DatabaseException;
}
