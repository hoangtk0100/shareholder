package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonDaoImpl extends CommonHibernate<Person> implements PersonDao {

    @Override
    public Person retrieveById(UUID id) throws NotFoundException {
        String sql = "from Person p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("id", id);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Person retrieveByPhoneNumber(String phoneNumber) throws NotFoundException {
        String sql = "from Person p where p.phoneNumber = :phoneNumber";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("phoneNumber", phoneNumber);
            return (Person) query.getResultList().get(0);
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Person retrieveByUsername(String username) throws NotFoundException {
        String sql = "from Person p where p.username = :username";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("username", username);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Person retrieveByPersonalId(String personalId) throws NotFoundException {
        String sql = "from Person p where p.personalId = :personalId";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("personalId", personalId);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Person";
    }
}