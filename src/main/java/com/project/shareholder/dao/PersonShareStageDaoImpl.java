package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.PersonShareStage;
import com.project.shareholder.util.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonShareStageDaoImpl extends CommonHibernate<PersonShareStage> implements PersonShareStageDao {
    @Override
    public PersonShareStage retrieveById(UUID id) throws NotFoundException {
        String sql = "from person_share_stage p where p.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShareStage.class)
                    .setParameter("id", id);
            return (PersonShareStage) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public PersonShareStage retrieveByStageId(UUID stageId) throws NotFoundException {
        String sql = "from person_share_stage p where p.stage_id = :stageId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShareStage.class)
                    .setParameter("stageId", stageId);
            return (PersonShareStage) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public PersonShareStage retrieveByPersonId(UUID personId) throws NotFoundException {
        String sql = "from person_share_stage p where p.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShareStage.class)
                    .setParameter("personId", personId);
            return (PersonShareStage) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public PersonShareStage retrieveByPersonStage(UUID stageId, UUID personId) throws NotFoundException {
        String sql = "from person_share_stage p where p.stage_id = :stageId and p.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, PersonShareStage.class)
                    .setParameter("stageId", stageId)
                    .setParameter("personId", personId);
            return (PersonShareStage) query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "PersonShareStage";
    }
}
