package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.model.Person;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ActivityDaoImpl extends CommonHibernate<Activity> implements ActivityDao {
    @Override
    public Activity retrieveById(UUID id) throws NotFoundException {
        String sql = "from Activity a where a.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Activity.class)
                    .setParameter("id", id);
            return (Activity) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<Activity> retrieveByPersonId(Person person) throws NotFoundException {
        String sql = "from Activity a where a.person = :person";
        try {
            Query query = getCurrentSession().createQuery(sql, Activity.class)
                    .setParameter("person", person);
            return query.getResultList();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Activity";
    }
}
