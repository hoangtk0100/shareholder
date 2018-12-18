package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class QuarterDaoImpl extends CommonHibernate<Quarter> implements QuarterDao {

    @Override
    public Quarter retrieveById(UUID id) throws NotFoundException {
        String sql = "from Quarter q where q.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Quarter.class)
                    .setParameter("id", id);
            return (Quarter) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Quarter retrieveByName(String name) throws NotFoundException {
        String sql = "from Quarter q where q.name = :name";
        try {
            Query query = getCurrentSession().createQuery(sql, Quarter.class)
                    .setParameter("name", name);
            return (Quarter) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Quarter retrieveByPeriod(Date period) throws NotFoundException {
        String sql = "from Quarter q where :period between q.dateStartedAt and q.dateEndedAt";
        try {
            Query query = getCurrentSession().createQuery(sql, Quarter.class)
                    .setParameter("period", period);
            return (Quarter) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Quarter";
    }
}
