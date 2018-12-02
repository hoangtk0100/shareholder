package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.*;
import com.project.shareholder.model.Person;
import org.hibernate.query.Query;
import com.project.shareholder.util.Constants;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonDaoImpl extends CommonHibernate<Person> implements PersonDao {

    @Override
    public Person retrieveById(UUID id) throws DatabaseException {
        String sql = "from person p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("id", id);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public Person retrieveByPhoneNumber(String phoneNumber) throws DatabaseException {
        String sql = "from person p where p.phone_number = :phoneNumber";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("phoneNumber", phoneNumber);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public Person retrieveByUsername(String username) throws DatabaseException {
        String sql = "from person p where p.username = :username";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("username", username);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Person";
    }
}