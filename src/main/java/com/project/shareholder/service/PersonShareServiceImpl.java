package com.project.shareholder.service;

import com.project.shareholder.dao.PersonShareDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonShare;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.PersonShareRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PersonShareServiceImpl implements PersonShareService {
    @Autowired
    private PersonShareDao personShareDao;

    @Override
    public PersonShare add(PersonShareRequest personShareRequest) throws DatabaseException {
        PersonShare personShare = new PersonShare();
        try {
            Person person = new Person();
            person.setId(UUID.fromString(personShareRequest.getPerson().getId()));
            personShare.setPerson(person);

            Stage stage = new Stage();
            stage.setId(UUID.fromString(personShareRequest.getStage().getId()));
            personShare.setStage(stage);
            personShare.setNote("Add");
            personShare.setStockQuantity(personShareRequest.getStockQuantity());
            personShare.setPeriod(personShareRequest.getPeriod());
            personShareDao.createObj(personShare);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personShare;
    }

    @Override
    public PersonShare subtract(PersonShareRequest personShareRequest) throws DatabaseException {
        PersonShare personShare = new PersonShare();
        try {
            Person person = new Person();
            person.setId(UUID.fromString(personShareRequest.getPerson().getId()));
            personShare.setPerson(person);

            Stage stage = new Stage();
            stage.setId(UUID.fromString(personShareRequest.getStage().getId()));
            personShare.setStage(stage);
            personShare.setNote("Subtract");
            personShare.setStockQuantity(personShareRequest.getStockQuantity());
            personShare.setPeriod(personShareRequest.getPeriod());
            personShareDao.createObj(personShare);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personShare;
    }
}
