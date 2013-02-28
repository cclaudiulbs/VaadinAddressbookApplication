package com.cc.addressbook.views;

import java.util.List;

import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * 
 */

public interface ShowAllContactsView extends ComponentContainer {

    void addListener(ShowAllContactsListener listener);
	void addContacts(List<PersonEntity> customers);

    public interface ShowAllContactsListener {
        void showContacts();
    }
}