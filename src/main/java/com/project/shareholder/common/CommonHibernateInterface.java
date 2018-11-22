package com.project.shareholder.common;

import com.project.shareholder.exception.DatabaseException;
import org.hibernate.Session;

import java.util.*;

public interface CommonHibernateInterface<Serializable, T> {

    /**
     * As the save() method from Hibernate returns a Serializable object
     * and it also update the passed parameter T, so we do not need to return obj again.
     * @param t
     */
    public void saveObj(Serializable t);

    /**
     * Update object in DB
     * @param t
     */
    public void updateObj(Serializable t);

    /**
     * Delete object from DB
     * @param t
     */
    public void deleteObj(Serializable t);

    /**
     * Delete objects from DB
     * @param objs
     */
    public void deleteObjs(Collection<T> objs);

    /**
     * Find all objects
     * @return
     */
    public List<T> findAll();

    /**
     * Find object by id in DB
     * @param id
     * @return
     * @throws DatabaseException
     */
    public T findObjById(Long id) throws DatabaseException;

    /**
     * Get table name in DB
     * @return
     */
    public String getTableName();

    /**
     * Get current session
     * @return
     */
    public Session getCurrentSession();

    /**
     * Get current session
     * @param
     */
    public void shutdown(Serializable t);
}
