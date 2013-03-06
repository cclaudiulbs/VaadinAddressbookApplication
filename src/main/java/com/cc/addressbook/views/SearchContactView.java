package com.cc.addressbook.views;

import com.cc.addressbook.util.SearchCriteria;

/**
 * @author cclaudiu
 */

public interface SearchContactView extends DefaultView {

    void addPresenter(SearchContactListener presenter);

    void clearSearchFormEvent();

    SearchCriteria getSearchCriteria();

    public interface SearchContactListener {
        void searchContact();
        void clearSearchForm();
        void displaySearchContactView();
    }
}
