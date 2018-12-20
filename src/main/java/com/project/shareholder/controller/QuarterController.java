package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.request.QuarterRequest;
import com.project.shareholder.service.QuarterService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quarters")
public class QuarterController {
    @Autowired
    private QuarterService quarterService;

    // Create new quarter
    @PostMapping("/create")
    public Quarter create(@Valid @RequestBody QuarterRequest quarterRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return quarterService.create(quarterRequest);
    }

    // Update quarter
    @PutMapping("/update")
    public Quarter update(@Valid @RequestBody QuarterRequest quarterRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return quarterService.update(quarterRequest);
    }

    // Deactivate quarter
    @PutMapping("/deactivate/{id}")
    public Quarter deactivate(@PathVariable String id) throws DatabaseException {
        return quarterService.deactivate(id);
    }

    // Delete quarter
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws DatabaseException {
        return quarterService.delete(id);
    }

    // Retrieve all quarters
    @GetMapping("/all")
    public List<Quarter> retrieveAll() {
        return quarterService.list();
    }

    // Retrieve quarter by id
    @GetMapping("/showById/{id}")
    public Quarter retrieveById(@PathVariable String id) throws NotFoundException {
        return quarterService.retrieveById(id);
    }

    // Retrieve quarter by period
    @GetMapping("/showByPeriod/{period}")
    public Quarter retrieveByPeriod(@PathVariable String period) throws NotFoundException {
        return quarterService.retrieveByPeriod(period);
    }
}
