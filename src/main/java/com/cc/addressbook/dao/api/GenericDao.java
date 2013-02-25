package com.cc.addressbook.dao.api;

import com.cc.addressbook.entities.TableEntity;
import java.util.List;

/**
 * @author cclaudiu
 *
 */

public interface GenericDao<T extends TableEntity> {


    TableEntity saveEntity(T entity);

    T updateEntity(T entity);

    void deleteEntity(T entity);

    T findEntity(Class<T> entityClazz, Long id);

    List<T> findAll(Class<T> entityClazz);

    List<T> findEntitiesBetweenRange(Class<T> entity, String columnName, Long from, Long to);

    void deleteAll(Class<T> entityClazz);

    /**
     * include sorting based on passed in array strings
     */
    List<T> getWithPagination(Class<T> clazz, int start, int rowCount, String[] orderByParams);
}