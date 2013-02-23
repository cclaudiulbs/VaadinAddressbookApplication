package com.cc.addressbook.models;

import com.cc.addressbook.dao.api.GenericDao;
import com.cc.addressbook.dao.core.GenericDaoImpl;
import com.cc.addressbook.entities.PersonEntity;

import java.util.List;

/**
 * @author cclaudiu
 *
 */

public class CustomerModel {

    private GenericDao<PersonEntity> contactDao = new GenericDaoImpl<>();

    public List<PersonEntity> getCustomers() {
        return contactDao.findAll(PersonEntity.class);
    }

    public PersonEntity saveCustomer(PersonEntity personEntity) {
        return contactDao.saveEntity(personEntity);
    }
}