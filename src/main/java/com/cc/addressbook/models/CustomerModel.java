package com.cc.addressbook.models;

import java.util.List;

import com.cc.addressbook.dao.api.GenericDao;
import com.cc.addressbook.dao.core.GenericDaoImpl;
import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.entities.TableEntity;

/**
 * @author cclaudiu
 *
 */

public class CustomerModel {

    private GenericDao<PersonEntity> contactDao = new GenericDaoImpl<>();

    public List<PersonEntity> getCustomers() {
        return contactDao.findAll(PersonEntity.class);
    }

    public TableEntity saveCustomer(PersonEntity personEntity) {
        return contactDao.saveEntity(personEntity);
    }
}