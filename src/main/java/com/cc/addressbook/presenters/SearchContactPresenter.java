package com.cc.addressbook.presenters;

import java.util.List;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.util.SearchCriteria;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.SearchContactView.SearchContactListener;
import com.cc.addressbook.views.ShowAllContactsView;

/**
 * @author cclaudiu
 *
 */

public class SearchContactPresenter implements SearchContactListener {

	private final AddressbookMainView mainAppView;
	private SearchContactView searchContactView;
	private final ShowAllContactsView<PersonEntity> showAllContactsView;
	
	public SearchContactPresenter(AddressbookMainView mainAppView, 
								  SearchContactView searchContactView,
								  ShowAllContactsView<PersonEntity> showAllContactsView) {
		this.mainAppView = mainAppView;
		this.searchContactView = searchContactView;
		this.showAllContactsView = showAllContactsView;
		
		searchContactView.addPresenter(this);
	}
	
	@Override
	public void showSearchView(SearchCriteria searchCriteria) {
		List<PersonEntity> filteredContacts = filter(showAllContactsView.getContactsList(), searchCriteria);
	}

}