package com.project.shareholder.service;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.request.SharePeriodRequest;

public interface SharePeriodService {
    // Add share
    SharePeriod add(SharePeriodRequest sharePeriodRequest) throws DatabaseException;

    // Subtract share
    SharePeriod subtract(SharePeriodRequest sharePeriodRequest) throws DatabaseException;

    // Bonus share
    SharePeriod bonus(SharePeriodRequest sharePeriodRequest) throws DatabaseException;
}
