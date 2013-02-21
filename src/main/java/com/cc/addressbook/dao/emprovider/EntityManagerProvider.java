package com.cc.addressbook.dao.emprovider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cclaudiu
 *
 */

public final class EntityManagerProvider {

    private static EntityManager entityManager;
    private static final String PERSISTENCE_UNIT_NAME = "addressbook_unit";

    private EntityManagerProvider() {
    }

    public static final EntityManager getInstance() {
        if (entityManager == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
}