package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.model.Profit;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PersonProfitDaoImpl extends CommonHibernate<PersonProfit> implements PersonProfitDao {
    @Override
    public PersonProfit retrieveById(UUID id) throws NotFoundException {
        String sql = "from PersonProfit p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("id", id);
            return (PersonProfit) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<PersonProfit> retrieveByPerson(Person person) throws NotFoundException {
        String sql = "from PersonProfit p where p.person = :person";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("person", person);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<PersonProfit> retrieveByProfit(Profit profit) throws NotFoundException {
        String sql = "from PersonProfit p where p.profit = :profit";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("profit", profit);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public PersonProfit retrieveByPersonProfit(Person person, Profit profit) throws NotFoundException {
        String sql = "from PersonProfit p where p.person = :person and p.profit = :profit";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("person", person)
                    .setParameter("profit", profit);
            return (PersonProfit) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "PersonProfit";
    }
}
