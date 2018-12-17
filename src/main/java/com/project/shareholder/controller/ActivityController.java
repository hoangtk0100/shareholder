package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.request.ActivityRequest;
import com.project.shareholder.service.ActivityService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    // Create new activity
    @PostMapping("/create")
    public Activity create(@Valid @RequestBody ActivityRequest activityRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return activityService.create(activityRequest);
    }

    // Retrieve all activities
    @GetMapping("/all")
    public List<Activity> retrieveAll() {
        return activityService.list();
    }

    // Retrieve activity by id
    @GetMapping("/showById/{id}")
    public Activity retrieveById(@PathVariable String id) throws NotFoundException {
        return activityService.retrieveById(id);
    }

    // Retrieve activity by person id
    @GetMapping("/showByPersonId/{personId}")
    public List<Activity> retrieveByPersonId(@PathVariable String personId) throws NotFoundException {
        return activityService.retrieveByPersonId(personId);
    }
}
