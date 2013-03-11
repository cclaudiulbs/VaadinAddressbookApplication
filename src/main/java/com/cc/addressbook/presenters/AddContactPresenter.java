package com.cc.addressbook.presenters;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.views.AddContactView;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.ShowAllContactsView;

import java.util.Arrays;

public class AddContactPresenter implements AddContactView.AddContactListener {

    private AddressbookMainView mainAppView;
    private final AddContactView addContactView;
    private ShowAllContactsView showAllView;

    public AddContactPresenter(AddressbookMainView mainAppView, AddContactView addContactView, ShowAllContactsView showAllView) {
        this.mainAppView = mainAppView;
        this.addContactView = addContactView;
        this.showAllView = showAllView;

        addContactView.addListener(this);
    }

    @Override
    public void addContact(PersonEntity contact) {
        // TODO: call the AddContact Model, to persist this contact in DB, and also refresh the UI List
        refreshAllContacts(contact);
    }

    @Override
    public void dispatchToAddContactView() {
        mainAppView.setMainViewSecondComponent(addContactView);
    }

    @Override
    public void clearAddContactForm() {
        addContactView.reset();
    }

    private void refreshAllContacts(PersonEntity newContact) {
        // refresh should do a "showAllContacts" query from DB to fetch contacts including new added one
        showAllView.addContacts(Arrays.asList(newContact));
        mainAppView.setMainViewFirstComponent(showAllView);
    }
}
