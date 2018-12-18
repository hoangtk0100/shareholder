package com.project.shareholder.service;

import com.project.shareholder.exception.AuthenticationException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.LoginRequest;

public interface LoginService {
    // Login
    Person login(LoginRequest loginRequest) throws AuthenticationException;
}
