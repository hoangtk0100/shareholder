package com.project.shareholder.controller;

import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.LoginRequest;
import com.project.shareholder.service.LoginService;
import com.project.shareholder.util.Constants;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public Person login(@Valid @RequestBody LoginRequest loginRequest, Errors errors) throws NotFoundException {
        try {
            // Validate input
            Utility.validateErrorsRequest(errors);
            Person person = loginService.login(loginRequest);

            return person;
        } catch (Exception ex) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }
}
