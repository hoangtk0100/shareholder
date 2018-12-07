package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonShare;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonShareDaoImpl extends CommonHibernate<PersonShare> implements PersonShareDao {
    @Override
    public PersonShare retrieveById(UUID id) throws NotFoundException {
        String sql = "from person_share p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShare.class)
                    .setParameter("id", id);
            return (PersonShare) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonShare retrieveByPersonId(UUID personId) throws NotFoundException {
        String sql = "from person_share p where p.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShare.class)
                    .setParameter("id", personId);
            return (PersonShare) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonShare retrieveByStage(UUID stageId) throws NotFoundException {
        String sql = "from person_share p where p.stage_id = :stageId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShare.class)
                    .setParameter("stageId", stageId);
            return (PersonShare) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public PersonShare retrieveByStage(UUID stageId, UUID personId) throws NotFoundException {
        String sql = "from person_share p where p.person_id = :personId  and p.stage_id = :stageId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShare.class)
                    .setParameter("personId", personId)
                    .setParameter("stageId", stageId);
            return (PersonShare) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "PersonShare";
    }
}
