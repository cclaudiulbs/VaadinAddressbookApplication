package com.cc.addressbook.presenters;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.FilterContactsServiceModel;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.ShowAllContactsView;

import java.util.List;

/**
 * @author cclaudiu
 *
 */
public class SearchContactFilterPresenter implements SearchContactView.SearchContactListener {

    private AddressbookMainView mainAppView;
    private SearchContactView searchContactView;
    private final ShowAllContactsView showAllContactsView;

    public SearchContactFilterPresenter(AddressbookMainView mainAppView,
                                        SearchContactView searchContactView,
                                        ShowAllContactsView showAllContactsView)
    {
        this.mainAppView = mainAppView;
        this.searchContactView = searchContactView;
        this.showAllContactsView = showAllContactsView;

        searchContactView.addPresenter(this);
    }

    // ------------ Handle searchContact Event --------------//
    @Override
    public void searchContact() {
        final FilterContactsServiceModel<PersonEntity> service = new FilterContactsServiceModel<>();

        List<PersonEntity> filteredContacts = service.filter(showAllContactsView.getContactsList(), searchContactView.getSearchCriteria());

        showAllContactsView.addContacts(filteredContacts);
    }

    // ----------- Handle clear Search Form Event ------------//
    @Override
    public void clearSearchForm() {
        searchContactView.clearSearchFormEvent();
    }

    @Override
    public void displaySearchContactView() {
        mainAppView.setMainViewSecondComponent(searchContactView);
    }

}
