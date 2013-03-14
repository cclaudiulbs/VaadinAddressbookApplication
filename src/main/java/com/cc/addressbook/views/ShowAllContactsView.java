package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;

import java.util.List;

/**
 * @author cclaudiu
 */

public interface ShowAllContactsView extends DefaultView {

    void addPresenter(ShowContactsListener showContactslistener);

    void addPresenter(DeleteContactOneByOneListener deleteContactsListener);

    void addContacts(List<PersonEntity> customers);

    void deleteContactOneByOne();

    void displayDeleteOptions();

    void removeDeleteOptions();

    interface ShowContactsListener {
        void showContacts();

        void displayDeleteOption();
    }

    interface DeleteContactOneByOneListener {
        void deleteContact();
    }

    List<PersonEntity> getContactsList();
}