package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernateInterface;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.model.Profit;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface PersonProfitDao extends CommonHibernateInterface<Serializable, PersonProfit> {
    // Retrieve person's profit by id
    PersonProfit retrieveById(UUID id) throws NotFoundException;

    // Retrieve person's profit by person
    List<PersonProfit> retrieveByPerson(Person person) throws NotFoundException;

    // Retrieve person's profit by profit
    List<PersonProfit> retrieveByProfit(Profit profit) throws NotFoundException;

    // Retrieve person's profit by person - profit
    PersonProfit retrieveByPersonProfit(Person person, Profit profit) throws NotFoundException;
}
