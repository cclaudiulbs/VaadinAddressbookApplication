package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * 
 */

public interface ShowAllContactsView extends ComponentContainer {

    void addListener(ShowAllContactsListener listener);

    public interface ShowAllContactsListener {
        void populateContainer(BeanItemContainer<PersonEntity> container);
    }

    BeanItemContainer<PersonEntity> getContactsContainer();
}