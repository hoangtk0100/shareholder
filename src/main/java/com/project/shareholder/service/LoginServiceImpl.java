package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.RoleDao;
import com.project.shareholder.exception.AuthenticationException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.LoginRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PersonDao personDao;

    // Login
    @Override
    public Person login(LoginRequest loginRequest) throws AuthenticationException {
        Person person = new Person();
        try {
            person = personDao.retrieveByUsername(loginRequest.getUsername());
            if (!person.getPassword().equals(loginRequest.getPassword())) {
                throw new AuthenticationException(Constants.AUTHENTICATION_MESSAGE);
            }
        } catch (NotFoundException e) {
            throw new AuthenticationException("Account is not valid");
        }

        return person;
    }
}
