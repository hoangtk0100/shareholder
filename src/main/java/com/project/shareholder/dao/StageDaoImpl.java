package com.project.shareholder.dao;

import com.project.shareholder.common.CommonHibernate;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.util.Constants;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.UUID;

@Repository
public class StageDaoImpl extends CommonHibernate<Stage> implements StageDao {

    @Override
    public Stage retrieveById(UUID id) throws NotFoundException {
        String sql = "from Stage s where s.id = :id";
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
        String sql = "from Stage s where s.name = :name";
        try {
            Query query = getCurrentSession().createQuery(sql, Stage.class)
                    .setParameter("name", name);
            return (Stage) query.getSingleResult();
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Stage retrieveByPeriod(YearMonth period) throws NotFoundException {
        String sql = "from Stage s where month(s.dateCreatedAt)= month(:period) and year(s.dateCreatedAt) = year(:period)";
        try {
            Query query = getCurrentSession().createQuery(sql, Stage.class)
                    .setParameter("period", period);
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
