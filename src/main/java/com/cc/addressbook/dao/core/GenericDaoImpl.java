package com.cc.addressbook.dao.core;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cc.addressbook.dao.api.GenericDao;
import com.cc.addressbook.dao.emprovider.EntityManagerProvider;
import com.cc.addressbook.entities.TableEntity;

/**
 * @author cclaudiu
 *
 */

public class GenericDaoImpl<T extends TableEntity> implements GenericDao<T> {
	private static Logger LOG = Logger.getLogger(GenericDao.class.getName());
	
    private EntityManager entityManager = EntityManagerProvider.getInstance();

    @Override public TableEntity saveEntity(T entity) {
        if(entity.getId() != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
        
        return entityManager.find(entity.getClass(), entity.getId());
    }


    @Override public T updateEntity(T entity) {
        return entityManager.merge(entity);
    }

    @Override public void deleteEntity(T entity) {
        entityManager.remove(entity);
    }

    @Override public T findEntity(Class<T> entityClazz, Long id) {
        return entityManager.find(entityClazz, id);
    }

    // Using CriteriaQuery
    @Override public List<T> findAll(Class<T> entityClazz) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
        Root<T> fromTable = criteriaQuery.from(entityClazz);
        criteriaQuery.select(fromTable);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    // Using JPQL
    @Override public List<T> findEntitiesBetweenRange(Class<T> entityClazz, String columnName, Long from, Long to) {
        final StringBuilder queryBuilder = new StringBuilder()
                .append("SELECT e FROM ")
                .append(entityClazz.getSimpleName())
                .append(" e WHERE e.%1s BETWEEN %2s AND %3s");

        final String formattedQuery = String.format(queryBuilder.toString(), columnName, from, to);
        final TypedQuery<T> query = entityManager.createQuery(formattedQuery, entityClazz);

        return query.getResultList();
    }

    public void deleteAll(Class<T> entityClazz) {
        final StringBuilder queryBuilder = new StringBuilder()
                .append("DELETE FROM ")
                .append(entityClazz.getSimpleName());

        EntityTransaction transaction = entityManager.getTransaction();

        if(!transaction.isActive()) {
            transaction.begin();
        }

        try {
            entityManager.createQuery(queryBuilder.toString(), entityClazz).executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            LOG.severe(e.getLocalizedMessage());

            if(transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<T> getWithPagination(Class<T> clazz, int start, int rowCount, String[] orderByParams) {

        if(orderByParams == null) {
            throw new RuntimeException("Cannot order based on null!");
        }

        final StringBuilder queryBuilder = new StringBuilder()
                .append(" SELECT e FROM ")
                .append(clazz.getSimpleName())
                .append(" e ");


        for(int idx = 0; idx < orderByParams.length; ++idx) {
            if(idx == 0) {
                queryBuilder.append(" ORDER BY ")
                        .append("e.").append(orderByParams[idx]);
            } else {
                queryBuilder.append(", ")
                        .append("e.").append(orderByParams[idx]);
            }
        }
        queryBuilder.append(" ASC ");

        TypedQuery<T> query = entityManager.createQuery(queryBuilder.toString(), clazz);
        query.setFirstResult(start);
        query.setMaxResults(rowCount);

        return query.getResultList();
    }
}
