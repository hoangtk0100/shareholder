package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SharePeriodDaoImpl extends CommonHibernate<SharePeriod> implements SharePeriodDao {
    @Override
    public SharePeriod retrieveById(UUID id) throws NotFoundException {
        String sql = "from SharePeriod s where s.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("id", id);
            return (SharePeriod) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<SharePeriod> retrieveByPersonQuarter(PersonQuarter personQuarter) throws NotFoundException {
        String sql = "from SharePeriod s where s.personQuarter = :personQuarter";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("personQuarter", personQuarter);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "SharePeriod";
    }
}
