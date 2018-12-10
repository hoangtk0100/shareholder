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
        String sql = "from person p where p.id = :id";
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
        String sql = "from person p where p.phone_number = :phoneNumber";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("phoneNumber", phoneNumber);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Person retrieveByUsername(String username) throws NotFoundException {
        String sql = "from person p where p.username = :username";
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
        String sql = "from person p where p.personal_id = :personalId";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("personalId", personalId);
            return (Person) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public double retrieveTotalStock(UUID id) throws NotFoundException {
        String sql = "select total_stock from person p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("id", id);
            return (double) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public void updateTotalStock(UUID id, double stockQuantity) throws DatabaseException {
        String sql = "update person set total_stock = :stockQuantity where id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Person.class)
                    .setParameter("id", id)
                    .setParameter("stockQuantity", stockQuantity);
             query.executeUpdate();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Person";
    }
}