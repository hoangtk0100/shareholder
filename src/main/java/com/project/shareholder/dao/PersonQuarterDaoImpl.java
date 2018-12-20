package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PersonQuarterDaoImpl extends CommonHibernate<PersonQuarter> implements PersonQuarterDao {
    @Override
    public PersonQuarter retrieveById(UUID id) throws NotFoundException {
        String sql = "from PersonQuarter p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonQuarter.class)
                    .setParameter("id", id);
            return (PersonQuarter) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<PersonQuarter> retrieveByQuarter(Quarter quarter) throws NotFoundException {
        String sql = "from PersonQuarter p where p.quarter = :quarter";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonQuarter.class)
                    .setParameter("quarter", quarter);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<PersonQuarter> retrieveByPerson(Person person) throws NotFoundException {
        String sql = "from PersonQuarter p where p.person = :person";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonQuarter.class)
                    .setParameter("person", person);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public PersonQuarter retrieveByPersonQuarter(Person person, Quarter quarter) throws NotFoundException {
        String sql = "from PersonQuarter p where p.quarter = :quarter and p.person = :person";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonQuarter.class)
                    .setParameter("quarter", quarter)
                    .setParameter("person", person);
            return (PersonQuarter) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "PersonQuarter";
    }
}
