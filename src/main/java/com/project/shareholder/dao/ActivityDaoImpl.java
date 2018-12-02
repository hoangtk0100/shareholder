package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;

import java.util.UUID;

public class ActivityDaoImpl extends CommonHibernate<Activity> implements ActivityDao {
    @Override
    public Activity retrieveById(UUID id) throws DatabaseException {
        String sql = "from activity a where a.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Activity.class)
                    .setParameter("id", id);
            return (Activity) query.getSingleResult();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public Activity retrieveByPersonId(UUID personId) throws DatabaseException {
        String sql = "from activity a where a.person_id = :personId";
        try {
            Query query = getCurrentSession().createQuery(sql, Activity.class)
                    .setParameter("personId", personId);
            return (Activity) query.getResultList();
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Activity";
    }
}
