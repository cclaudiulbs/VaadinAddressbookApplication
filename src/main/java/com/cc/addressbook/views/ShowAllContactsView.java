package com.cc.addressbook.views;

import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * 
 */

public interface ShowAllContactsView extends ComponentContainer {

    void addListener(ShowAllContactsListener listener);

    public interface ShowAllContactsListener {
        void showContacts();
    }
}