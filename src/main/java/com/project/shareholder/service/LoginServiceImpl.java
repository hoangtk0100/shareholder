package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.RoleDao;
import com.project.shareholder.exception.ApplicationException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.Role;
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
    public Person login(LoginRequest loginRequest) throws NotFoundException {
        Person person;

        try {
            person = personDao.retrieveById(loginRequest.getId());
            person.setUsername(loginRequest.getUsername());
            person.setFullName(loginRequest.getFullName());
            person.setEmail(loginRequest.getEmail());
            person.setAvatar(loginRequest.getAvatar());
            personDao.updateObj(person);
        } catch (ApplicationException exception) {
            person = new Person();
            person.setId(loginRequest.getId());
            person.setUsername(loginRequest.getUsername());
            person.setFullName(loginRequest.getFullName());
            person.setEmail(loginRequest.getEmail());
            person.setAvatar(loginRequest.getAvatar());

            Role role;
            try {
                role = roleDao.retrieveByName("USER");
            } catch (ApplicationException applicationException) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            person.setRole(role);
            personDao.createObj(person);
        }

        return person;
    }
}
