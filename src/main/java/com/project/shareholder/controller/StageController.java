package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.StageRequest;
import com.project.shareholder.service.StageService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {
    @Autowired
    private StageService stageService;

    // Create new stage
    @PostMapping("/create")
    public Stage create(@Valid @RequestBody StageRequest stageRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return stageService.create(stageRequest);
    }

    // Update stage
    @PutMapping("/update")
    public Stage update(@Valid @RequestBody StageRequest stageRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return stageService.update(stageRequest);
    }

    // Deactivate stage
    @PutMapping("/deactivate/{id}")
    public Stage deactivate(@PathVariable String id) throws DatabaseException {
        return stageService.deactivate(id);
    }

    // Delete stage
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws DatabaseException {
        return stageService.delete(id);
    }

    // Retrieve all stages
    @GetMapping("/all")
    public List<Stage> retrieveAll() {
        return stageService.list();
    }

    // Retrieve stage by id
    @GetMapping("/showById/{id}")
    public Stage retrieveById(@PathVariable String id) throws NotFoundException {
        return stageService.retrieveById(id);
    }

    // Retrieve stage by period
    @GetMapping("/showByPeriod/{period}")
    public Stage retrieveByPeriod(@PathVariable String period) throws NotFoundException {
        return stageService.retrieveByPeriod(period);
    }
}
