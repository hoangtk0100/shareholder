package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StageDaoImpl extends CommonHibernate<Stage> implements StageDao {

    @Override
    public Stage retrieveById(UUID id) throws NotFoundException {
        String sql = "from stage s where s.id = :id";
        try {
            Query query = getCurrentSession().createQuery(sql, Stage.class)
                    .setParameter("id", id);
            return (Stage) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Stage retrieveByName(String name) throws NotFoundException {
        String sql = "from stage s where s.name = :name";
        try {
            Query query = getCurrentSession().createQuery(sql, Stage.class)
                    .setParameter("name", name);
            return (Stage) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String getTableName() {
        return "Stage";
    }
}
