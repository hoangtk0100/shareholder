package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Profit;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Date;
import java.util.UUID;

@Repository
public class ProfitDaoImpl extends CommonHibernate<Profit> implements ProfitDao {
    @Override
    public Profit retrieveById(UUID id) throws NotFoundException {
        String sql = "from Profit p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Profit.class)
                    .setParameter("id", id);
            return (Profit) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Profit retrieveByPeriod(YearMonth period) throws NotFoundException {
        String sql = "from Profit p where p.period = :period";
        try {
            Query query = getCurrentSession().createQuery(sql, Profit.class)
                    .setParameter("period", period);
            return (Profit) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Profit";
    }
}