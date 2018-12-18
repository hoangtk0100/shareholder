package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonShareDaoImpl extends CommonHibernate<SharePeriod> implements PersonShareDao {
    @Override
    public SharePeriod retrieveById(UUID id) throws NotFoundException {
        String sql = "from person_share p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("id", id);
            return (SharePeriod) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public SharePeriod retrieveByPersonId(UUID personId) throws NotFoundException {
        String sql = "from person_share p where p.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("id", personId);
            return (SharePeriod) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public SharePeriod retrieveByStage(UUID stageId) throws NotFoundException {
        String sql = "from person_share p where p.stage_id = :stageId";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("stageId", stageId);
            return (SharePeriod) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public SharePeriod retrieveByStage(UUID stageId, UUID personId) throws NotFoundException {
        String sql = "from person_share p where p.person_id = :personId  and p.stage_id = :stageId";
        try {
            Query query = getCurrentSession().createQuery(sql, SharePeriod.class)
                    .setParameter("personId", personId)
                    .setParameter("stageId", stageId);
            return (SharePeriod) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "SharePeriod";
    }
}
