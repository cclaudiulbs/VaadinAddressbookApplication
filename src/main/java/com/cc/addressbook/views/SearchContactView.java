package com.cc.addressbook.views;

import com.cc.addressbook.util.SearchCriteria;

/**
 * @author cclaudiu
 */

public interface SearchContactView extends DefaultView {

    void addPresenter(SearchContactListener presenter);

    SearchCriteria getSearchCriteria();

    void clearSearchFormEvent();

    public interface SearchContactListener {
        void searchContact();
    }

    public interface ClearContactSearchListener {
        void clearSearchFormListener();
    }
}
