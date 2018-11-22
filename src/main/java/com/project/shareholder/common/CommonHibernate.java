package com.project.shareholder.common;

import com.project.shareholder.exception.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class CommonHibernate<T> implements CommonHibernateInterface<Serializable, T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveObj(Serializable t) {
        if(t instanceof CommonSerialize) {
            if(((CommonSerialize) t).getId().isEmpty() || ((CommonSerialize) t).getId().isEmpty()) {
                ((CommonSerialize) t).setId(UUID.randomUUID());
            }
        }

        sessionFactory.getCurrentSession().save(t);
    }

    @Override
    public void updateObj(Serializable t) {
        sessionFactory.getCurrentSession().update(t);
    }

    @Override
    public void deleteObj(Serializable t) {
        if(t != null){
            sessionFactory.getCurrentSession().delete(t);
        }
    }

    @Override
    public void deleteObjs(Collection<T> objs) {
        for(T obj : objs) {
            this.deleteObj((Serializable) obj);
        }
    }

    @Override
    public List<T> findAll() {
        String queryString = "from " + getTableName();
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public T findObjById(Long id) throws DatabaseException {
        // Create object class of parameter Type first.
        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        try {
            return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
        } catch (Exception e) {
            throw new DatabaseException("Database Exception");
        }
    }

    @Override
    public void shutdown(Serializable t) {
        sessionFactory.getCurrentSession().close();
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}

