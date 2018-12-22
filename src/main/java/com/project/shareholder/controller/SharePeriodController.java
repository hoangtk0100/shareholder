package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.request.SharePeriodRequest;
import com.project.shareholder.service.SharePeriodService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sharePeriods")
public class SharePeriodController {
    @Autowired
    private SharePeriodService sharePeriodService;

    // Add share period
    @PostMapping("/add")
    public SharePeriod add(@Valid @RequestBody SharePeriodRequest sharePeriodRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return sharePeriodService.add(sharePeriodRequest);
    }

    // Subtract share period
    @PostMapping("/subtract")
    public SharePeriod subtract(@Valid @RequestBody SharePeriodRequest sharePeriodRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return sharePeriodService.subtract(sharePeriodRequest);
    }

    // Bonus share period
    @PostMapping("/bonus")
    public SharePeriod bonus(@Valid @RequestBody SharePeriodRequest sharePeriodRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return sharePeriodService.bonus(sharePeriodRequest);
    }
}
