package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;

/**
 * @author cclaudiu
 *
 */

public interface AddContactView extends DefaultView {

    void addListener(AddContactListener addContactListener);
    void reset();

    interface AddContactListener {
        void addContact(PersonEntity contact);
        void dispatchToAddContactView();
        void clearAddContactForm();
    }
}
