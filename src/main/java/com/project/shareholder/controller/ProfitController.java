package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Profit;
import com.project.shareholder.request.ProfitRequest;
import com.project.shareholder.service.ProfitService;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profits")
public class ProfitController {
    @Autowired
    private ProfitService profitService;

    // Create new profit
    @PostMapping("/create")
    public Profit create(@Valid @RequestBody ProfitRequest profitRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }

        return profitService.create(profitRequest);
    }

    // Retrieve all activities
    @GetMapping("/all")
    public List<Profit> retrieveAll() {
        return profitService.list();
    }

    // Retrieve profit by id
    @GetMapping("/showById/{id}")
    public Profit retrieveById(@PathVariable String id) throws NotFoundException {
        return profitService.retrieveById(id);
    }

    // Retrieve profit by period
    @GetMapping("/showByPeriod/{period}")
    public Profit retrieveByPeriod(@PathVariable String period) throws NotFoundException {
        return profitService.retrieveByPeriod(period);
    }
}
