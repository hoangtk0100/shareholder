package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.*;
import com.project.shareholder.model.Profit;
import org.hibernate.query.Query;
import com.project.shareholder.util.Constants;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.Year;
import java.time.YearMonth;
import java.util.UUID;

@Repository
public class ProfitDaoImpl extends CommonHibernate<Profit> implements ProfitDao {
    @Override
    public Profit retrieveById(UUID id) throws DatabaseException {
        String sql = "from profit p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Profit.class)
                    .setParameter("id", id);
            return (Profit) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public Profit retrieveByPeriod(YearMonth period) throws DatabaseException {
        String sql = "from profit p where month(p.period)= month(:period) and year(p.period) = year(:period)";
        try {
            Query query = getCurrentSession().createQuery(sql, Profit.class)
                    .setParameter("period", period);
            return (Profit) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Profit";
    }
}