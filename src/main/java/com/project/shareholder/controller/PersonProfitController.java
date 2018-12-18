package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.request.PersonProfitRequest;
import com.project.shareholder.service.PersonProfitService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personProfits")
public class PersonProfitController {
    @Autowired
    private PersonProfitService personProfitService;

    // Create new person's profit
    @PostMapping("/create")
    public PersonProfit create(@Valid @RequestBody PersonProfitRequest personProfitRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return personProfitService.create(personProfitRequest);
    }

    // Retrieve all person's profits
    @GetMapping("/all")
    public List<PersonProfit> retrieveAll() {
        return personProfitService.list();
    }

    // Retrieve person's profit by id
    @GetMapping("/showById/{id}")
    public PersonProfit retrieveById(@PathVariable String id) throws NotFoundException {
        return personProfitService.retrieveById(id);
    }

    // Retrieve person's profit by person id
    @GetMapping("/showByPersonId/{personId}")
    public List<PersonProfit> retrieveByPersonId(@PathVariable String personId) throws NotFoundException {
        return personProfitService.retrieveByPersonId(personId);
    }

    // Retrieve person's profit by profit id
    @GetMapping("/showByProfitId/{profitId}")
    public List<PersonProfit> retrieveByProfitId(@PathVariable String profitId) throws NotFoundException {
        return personProfitService.retrieveByProfitId(profitId);
    }

    // Retrieve person's profit by person and profit
    @GetMapping("/showByPersonProfit/{personId}/{profitId}")
    public PersonProfit retrieveByPersonProfit(@PathVariable("personId") String personId, @PathVariable("profitId") String profitId) throws NotFoundException {
        return personProfitService.retrieveByPersonProfit(personId, profitId);
    }
}
