package com.omnimanage.infrastructure.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRepository<T, P extends Serializable>{
    private final Class<T> entityClass;

    protected AbstractRepository(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    public T save(T e)
    {
        try
        {
            e = getEntityManager().merge(e);
            getEntityManager().flush();
            getEntityManager().clear();

        }
        catch (PersistenceException ex)
        {
            getEntityManager().clear();
        }
        return e;
    }

    public void delete(T entity)
    {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(P id)
    {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> find()
    {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }


    protected abstract EntityManager getEntityManager();
}