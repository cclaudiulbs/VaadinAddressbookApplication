package com.cc.addressbook.presenters;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.SearchContactView;
import com.vaadin.Application;

/**
 * @author cclaudiu
 *
 * SearchContactViewPresenter is only rendering the SearchContactView which has some
 * logic of fireing SearchEvents handled by the SearchContactFilterPresenter
 */

public class SearchContactViewPresenter implements SearchContactView.ClearContactSearchListener {

	private final AddressbookMainView mainAppView;
   private final SearchContactView searchContactView;

	public SearchContactViewPresenter(AddressbookMainView mainAppView, SearchContactView searchContactView) {
		this.mainAppView = mainAppView;
      this.searchContactView = searchContactView;

       searchContactView.addPresenter((SearchContactView.SearchContactListener) this);
	}


   public void displaySearchContactView() {
       mainAppView.setMainViewSecondComponent(searchContactView);
   }

    @Override
    public void clearSearchFormListener() {
        searchContactView.clearSearchFormEvent();
    }
}