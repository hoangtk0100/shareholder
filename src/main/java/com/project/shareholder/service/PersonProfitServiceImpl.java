package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonProfitDao;
import com.project.shareholder.dao.ProfitDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.request.PersonProfitRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
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
            personProfit.setId(UUID.fromString(personProfitRequest.getId()));
            personProfit.setPeriodProfit(personProfitRequest.getPeriodProfit());
            personProfit.setPerson(personDao.retrieveById(UUID.fromString(personProfitRequest.getPerson().getId())));
            personProfit.setProfit(profitDao.retrieveById(UUID.fromString(personProfitRequest.getProfit().getId())));
            personProfitDao.createObj(personProfit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personProfit;
    }

    // Retrieve person profit by id
    @Override
    public PersonProfit retrieveById(UUID id) throws NotFoundException {
        return personProfitDao.retrieveById(id);
    }

    // Retrieve person profit by person id
    @Override
    public PersonProfit retrieveByPersonId(UUID id) throws NotFoundException {
        return personProfitDao.retrieveByPersonId(id);
    }

    // Retrieve person profit by profit id
    @Override
    public PersonProfit retrieveByProfitId(UUID id) throws NotFoundException {
        return personProfitDao.retrieveByProfitId(id);
    }

    // Retrieve person profit by period
    @Override
    public PersonProfit retrieveByPeriod(YearMonth period) throws NotFoundException {
        return personProfitDao.retrieveByPeriod(period);
    }

    // Retrieve person profit by person profit
    @Override
    public PersonProfit retrieveByPersonProfit(UUID personId, UUID profitId) throws NotFoundException {
        return personProfitDao.retrieveByPersonProfit(personId, profitId);
    }
}
