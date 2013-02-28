package com.cc.addressbook.views;

import com.cc.addressbook.util.SearchCriteria;


public interface SearchContactView extends DefaultView {
	
	void addPresenter(SearchContactListener presenter);
	
	public interface SearchContactListener {
		void showSearchView(SearchCriteria searchCriteria);
	}
}
