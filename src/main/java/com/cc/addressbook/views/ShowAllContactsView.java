package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author cclaudiu
 *
 */

public interface ShowAllContactsView {

    void addListener(ShowAllContactsListener listener);

    public interface ShowAllContactsListener {
        void populateContainer(BeanItemContainer<PersonEntity> container);
    }

    BeanItemContainer<PersonEntity> getPersonContainer();
}