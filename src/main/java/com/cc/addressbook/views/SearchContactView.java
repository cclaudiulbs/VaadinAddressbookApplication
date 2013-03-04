package com.cc.addressbook.views;

import com.vaadin.ui.AbstractOrderedLayout;

/**
 * @author cclaudiu
 *
 */

public interface SearchContactView extends DefaultView {
	
	void addPresenter(SearchContactListener presenter);
    AbstractOrderedLayout getSearchForm();
	
	public interface SearchContactListener {
        void searchContact();
    }
}
