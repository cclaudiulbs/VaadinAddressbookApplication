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

    private SearchContactView searchContactView;
    private final ShowAllContactsView showAllContactsView;

    public SearchContactFilterPresenter(AddressbookMainView mainAppView,
                                        SearchContactView searchContactView,
                                        ShowAllContactsView showAllContactsView) {
        this.searchContactView = searchContactView;
        this.showAllContactsView = showAllContactsView;

        searchContactView.addPresenter(this);
    }

    @Override
    public void searchContact() {
        final FilterContactsServiceModel<PersonEntity> service = new FilterContactsServiceModel<>();

        List<PersonEntity> filteredContacts = service.filter(showAllContactsView.getContactsList(), searchContactView.getSearchCriteria());

        // TODO: when the "searchButton" is clicked the SearchCOntactView should be displayed and when the searchButton is clicked
        // this View should be updated with the search and filtered results
        showAllContactsView.addContacts(filteredContacts);
    }
}
