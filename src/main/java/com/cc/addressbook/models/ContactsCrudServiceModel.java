package com.cc.addressbook.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cc.addressbook.dao.api.GenericDao;
import com.cc.addressbook.dao.core.GenericDaoImpl;
import com.cc.addressbook.entities.JobEntity;
import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.entities.TableEntity;

/**
 * @author cclaudiu
 *
 */

public class ContactsCrudServiceModel {
    private boolean mockingLevel = Boolean.TRUE;

    private GenericDao<PersonEntity> contactDao = new GenericDaoImpl<>();

    public List<PersonEntity> getCustomers() {
        return mockingLevel == Boolean.TRUE ? getMockContacts(50) : contactDao.findAll(PersonEntity.class);
    }

    public TableEntity saveCustomer(PersonEntity personEntity) {
        return contactDao.saveEntity(personEntity);
    }

    private List<PersonEntity> getMockContacts(int bound) {
        List<PersonEntity> mockContactList = new ArrayList<>(bound);

        for(int idx = 0; idx < bound; ++idx) {
            PersonEntity eachPerson = new PersonEntity();
            JobEntity eachPersonJob = new JobEntity();
            eachPersonJob.setId((long) idx);
            eachPersonJob.setJobDescription("vaadin developer" + idx);
            eachPersonJob.setJobTitle("Java Dev");

            eachPerson.setFirstName("claudiu_" + idx);
            eachPerson.setLastName("cosar_" + idx);
            eachPerson.setAddress("soarelui_" + idx);
            eachPerson.setEmail("claudiu_" + idx + ".cosar@gmail.com");
            eachPerson.setId((long) idx);
            eachPerson.setJob(Arrays.asList(eachPersonJob));
            eachPerson.setAge("" + idx);

            mockContactList.add(eachPerson);
        }
        return mockContactList;
    }
}