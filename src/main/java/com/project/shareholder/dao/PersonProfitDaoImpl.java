package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;

import java.time.YearMonth;
import java.util.UUID;

public class PersonProfitDaoImpl extends CommonHibernate<PersonProfit> implements PersonProfitDao {
    @Override
    public PersonProfit retrieveById(UUID id) throws DatabaseException {
        String sql = "from person_profit p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("id", id);
            return (PersonProfit) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonProfit retrieveByPersonId(UUID personId) throws DatabaseException {
        String sql = "from person_profit p where p.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("personId", personId);
            return (PersonProfit) query.getResultList();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonProfit retrieveByPeriod(YearMonth period) throws DatabaseException {
        String sql = "from person_profit p where month(p.period)= month(:period) and year(p.period) = year(:period)";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("period", period);
            return (PersonProfit) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonProfit retrieveByPersonPeriod(UUID personId, YearMonth period) throws DatabaseException {
        String sql = "from person_profit p where p.person_id = :personId and month(p.period)= month(:period) and year(p.period) = year(:period)";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonProfit.class)
                    .setParameter("personId", personId)
                    .setParameter("period", period);
            return (PersonProfit) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "PersonProfit";
    }
}
