package com.cc.addressbook.models;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.util.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 * Filter Contacts Service-Model
 */

public class FilterContactsServiceModel<T extends PersonEntity> {

    public List<T> filter(List<T> unfilteredContacts, SearchCriteria criteria) {
        List<T> filteredContacts = new ArrayList<>();

        for(T eachContact : unfilteredContacts) {
            if(eachContact.getFirstName().equalsIgnoreCase(criteria.getFirstName())
                    || eachContact.getLastName().equalsIgnoreCase((criteria.getLastName()))
                    || eachContact.getPhoneMobileNumber().equalsIgnoreCase(criteria.getPhoneNumber())) {

                filteredContacts.add(eachContact);
            }
        }

        return filteredContacts;
    }
}