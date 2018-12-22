package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.request.PersonQuarterRequest;
import com.project.shareholder.service.PersonQuarterService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personQuarters")
public class PersonQuarterController {
    @Autowired
    private PersonQuarterService personQuarterService;

    // Create new person - quarter
    @PostMapping("/create")
    public PersonQuarter create(@Valid @RequestBody PersonQuarterRequest personQuarterRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return personQuarterService.create(personQuarterRequest);
    }

    // Update person - quarter
    @PutMapping("/update")
    public PersonQuarter update(@Valid @RequestBody PersonQuarterRequest personQuarterRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return personQuarterService.update(personQuarterRequest);
    }

    // Deactivate person - quarter
    @PutMapping("/deactivate/{id}")
    public PersonQuarter deactivate(@PathVariable String id) throws DatabaseException {
        return personQuarterService.deactivate(id);
    }

    // Retrieve all person - quarters
    @GetMapping("/all")
    public List<PersonQuarter> retrieveAll() {
        return personQuarterService.list();
    }

    // Retrieve person - quarter by id
    @GetMapping("/showById/{id}")
    public PersonQuarter retrieveById(@PathVariable String id) throws NotFoundException {
        return personQuarterService.retrieveById(id);
    }

    // Retrieve person - quarter by person id
    @GetMapping("/showByPersonId/{personId}")
    public List<PersonQuarter> retrieveByPersonId(@PathVariable String personId) throws NotFoundException {
        return personQuarterService.retrieveByPersonId(personId);
    }

    // Retrieve person - quarter by quarter id
    @GetMapping("/showByQuarterId/{quarterId}")
    public List<PersonQuarter> retrieveByQuarterId(@PathVariable String quarterId) throws NotFoundException {
        return personQuarterService.retrieveByQuarterId(quarterId);
    }

    // Retrieve person - quarter by person - quarter
    @GetMapping("/showByPersonQuarter/{personId}/{quarterId}")
    public PersonQuarter retrieveByPersonQuarter(@PathVariable("personId") String personId, @PathVariable("quarterId") String quarterId) throws NotFoundException {
        return personQuarterService.retrieveByPersonQuarter(personId, quarterId);
    }

    // Retrieve person - quarter by person period
    @GetMapping("/showByPersonPeriod/{personId}/{period}")
    public PersonQuarter retrieveByPersonPeriod(@PathVariable("personId") String personId, @PathVariable("period") String period) throws NotFoundException {
        return personQuarterService.retrieveByPersonPeriod(personId, period);
    }
}
