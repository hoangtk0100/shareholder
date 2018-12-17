package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.RoleDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.Role;
import com.project.shareholder.request.PersonRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertDate;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Person create(PersonRequest personRequest) throws DatabaseException {
        Person person = new Person();
        String referrerUsername = personRequest.getReferrerUsername();
        person.setFullName(personRequest.getFullName());
        person.setUsername(personRequest.getUsername());
        person.setPassword(personRequest.getPassword());
        person.setEmail(personRequest.getEmail());
        person.setAddress(personRequest.getAddress());
        person.setBirthday(convertDate(personRequest.getBirthday()));
        person.setAvatar(personRequest.getAvatar());
        person.setGender(personRequest.isGender());
        person.setPersonalId(personRequest.getPersonalId());
        person.setPhoneNumber(personRequest.getPhoneNumber());
        person.setRefererUsername(referrerUsername);
        try {
            // Set role
            Role role;
            role = roleDao.retrieveById(UUID.fromString(personRequest.getRoleId()));
            person.setRole(role);

            // Create new person
            personDao.createObj(person);

            // Add referral
            if (!referrerUsername.trim().isEmpty()) {
                Person referrer = personDao.retrieveByUsername(referrerUsername);
                if (referrer == null) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }

                List<Person> persons = referrer.getReferrals();
                persons.add(person);
                referrer.setReferrals(persons);
                personDao.updateObj(referrer);
            }
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public Person update(PersonRequest personRequest) throws DatabaseException {
        Person person = new Person();
        person.setId(UUID.fromString(personRequest.getId()));
        person.setFullName(personRequest.getFullName());
        person.setUsername(personRequest.getUsername());
        person.setPassword(personRequest.getPassword());
        person.setEmail(personRequest.getEmail());
        person.setAddress(personRequest.getAddress());
        person.setBirthday(convertDate(personRequest.getBirthday()));
        person.setAvatar(personRequest.getAvatar());
        person.setGender(personRequest.isGender());
        person.setPersonalId(personRequest.getPersonalId());
        person.setPhoneNumber(personRequest.getPhoneNumber());
        try {
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public Person deactivate(String id) throws DatabaseException {
        Person person;
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            personDao.deactivateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public String delete(String id) throws DatabaseException {
        Person person;
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            personDao.deleteObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    @Override
    public Person retrieveById(String id) throws NotFoundException {
        return personDao.retrieveById(UUID.fromString(id));
    }

    @Override
    public Person retrieveByUsername(String username) throws NotFoundException {
        return personDao.retrieveByUsername(username);
    }

    @Override
    public Person retrieveByPersonalId(String personalId) throws NotFoundException {
        return personDao.retrieveByPersonalId(personalId);
    }

    @Override
    public Person retrieveByPhoneNumber(String phoneNumber) throws NotFoundException {
        return personDao.retrieveByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Person> list() {
        return (personDao.retrieveAll());
    }

    @Override
    public Person updateTotalStock(String id, double stockQuantity) throws DatabaseException {
        Person person;
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            person.setTotalStock(stockQuantity);
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public Person addReferral(PersonRequest personRequest) throws DatabaseException {
        Person referer;
        try {
            referer = personDao.retrieveByUsername(personRequest.getReferrerUsername());
            Person referral = personDao.retrieveById(UUID.fromString(personRequest.getId()));

            List<Person> persons = referer.getReferrals();
            persons.add(referral);
            referer.setReferrals(persons);
            personDao.updateObj(referer);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return referer;
    }
}
