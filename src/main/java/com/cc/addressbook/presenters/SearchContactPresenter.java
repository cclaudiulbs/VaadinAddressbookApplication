package com.cc.addressbook.presenters;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.ContactsCrudServiceModel;
import com.cc.addressbook.models.FilterContactsServiceModel;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.ShowAllContactsView;

import java.util.List;

/**
 * @author cclaudiu
 *
 */
public class SearchContactPresenter implements SearchContactView.SearchContactListener {

    private AddressbookMainView mainAppView;
    private SearchContactView searchContactView;
    private final ShowAllContactsView showAllContactsView;

    public SearchContactPresenter(AddressbookMainView mainAppView,
                                  SearchContactView searchContactView,
                                  ShowAllContactsView showAllContactsView)
    {
        this.mainAppView = mainAppView;
        this.searchContactView = searchContactView;
        this.showAllContactsView = showAllContactsView;

        this.searchContactView.addPresenter(this);
    }

    // ------------ Handle searchContact Event --------------//
    @Override
    public void searchContact() {
        final FilterContactsServiceModel<PersonEntity> service = new FilterContactsServiceModel<>();
        List<PersonEntity> contacts = showAllContactsView.getContactsList();

        if(contacts.isEmpty()) {
            final ContactsCrudServiceModel showContactService = new ContactsCrudServiceModel();
            contacts.clear();
            contacts.addAll(showContactService.getCustomers());             // TODO: refactor this service to use the Custom Query and NOT fetching all results from DB
        }

        List<PersonEntity> filteredContacts = service.filter(contacts, searchContactView.getSearchCriteria());

        showAllContactsView.addContacts(filteredContacts);
        mainAppView.setMainViewFirstComponent(showAllContactsView);
    }

    // ----------- Handle clear Search Form Event ------------//
    @Override
    public void clearSearchForm() {
        searchContactView.clearSearchFormEvent();
    }

    @Override
    public void displaySearchContactView() {
        mainAppView.clearSelectedComponents();
        mainAppView.setMainViewSecondComponent(searchContactView);
    }

}
