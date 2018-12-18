package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonProfitDao;
import com.project.shareholder.dao.ProfitDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.model.Profit;
import com.project.shareholder.request.PersonProfitRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PersonProfitServiceImpl implements PersonProfitService {
    @Autowired
    private PersonProfitDao personProfitDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ProfitDao profitDao;

    // Create person profit
    @Override
    public PersonProfit create(PersonProfitRequest personProfitRequest) throws DatabaseException {
        PersonProfit personProfit = new PersonProfit();
        try {
            Person person = personDao.retrieveById(personProfitRequest.getPersonId());
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            Profit profit = profitDao.retrieveById(personProfitRequest.getProfitId());
            if (null == profit) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            personProfit.setPerson(person);
            personProfit.setProfit(profit);
            personProfit.setPeriodProfit(personProfitRequest.getPeriodProfit());
            personProfitDao.createObj(personProfit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personProfit;
    }

    // Retrieve person profit by id
    @Override
    public PersonProfit retrieveById(String id) throws NotFoundException {
        return personProfitDao.retrieveById(UUID.fromString(id));
    }

    // Retrieve person profit by person id
    @Override
    public List<PersonProfit> retrieveByPersonId(String personId) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        return personProfitDao.retrieveByPerson(person);
    }

    // Retrieve person profit by profit id
    @Override
    public List<PersonProfit> retrieveByProfitId(String profitId) throws NotFoundException {
        Profit profit = profitDao.retrieveById(UUID.fromString(profitId));
        return personProfitDao.retrieveByProfit(profit);
    }

    // Retrieve person profit by person profit
    @Override
    public PersonProfit retrieveByPersonProfit(String personId, String profitId) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        Profit profit = profitDao.retrieveById(UUID.fromString(profitId));
        return personProfitDao.retrieveByPersonProfit(person, profit);
    }

    @Override
    public List<PersonProfit> list() {
        return personProfitDao.retrieveAll();
    }
}
