package com.cc.addressbook.presenters;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.FilterContactsServiceModel;
import com.cc.addressbook.util.SearchCriteria;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.SearchContactView.SearchContactListener;
import com.cc.addressbook.views.ShowAllContactsView;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.TextField;

import java.util.List;

/**
 * @author cclaudiu
 *
 */

public class SearchContactPresenter implements SearchContactListener {

	private final AddressbookMainView mainAppView;
	private SearchContactView searchContactView;
	private final ShowAllContactsView showAllContactsView;
	
	public SearchContactPresenter(AddressbookMainView mainAppView, 
								  SearchContactView searchContactView,
								  ShowAllContactsView showAllContactsView) {
		this.mainAppView = mainAppView;
		this.searchContactView = searchContactView;
		this.showAllContactsView = showAllContactsView;
		
		searchContactView.addPresenter(this);
	}
	
	@Override
	public void searchContact() {
        final FilterContactsServiceModel<PersonEntity> service = new FilterContactsServiceModel<>();

		List<PersonEntity> filteredContacts = service.filter(showAllContactsView.getContactsList(), getSearchCriteria());

        showAllContactsView.addContacts(filteredContacts);
	}

    // TODO: Presenter should NOT know what type of components the view has!
    private SearchCriteria getSearchCriteria() {
        AbstractOrderedLayout searchForm = searchContactView.getSearchForm();
        String searchFirstName = getSearchedField(searchForm, 0);
        String lastNameSearch = getSearchedField(searchForm, 1);
        String phoneNumSearch = getSearchedField(searchForm, 2);

        SearchCriteria criteria = new SearchCriteria.Builder()
                                        .firstName(searchFirstName)
                                        .lastName(lastNameSearch)
                                        .phoneNumber(phoneNumSearch)
                                        .build();
        return criteria;
    }

    private String getSearchedField(AbstractOrderedLayout searchForm, int idx) {
        return ((TextField) searchForm.getComponent(idx)).getValue().toString();
    }
}