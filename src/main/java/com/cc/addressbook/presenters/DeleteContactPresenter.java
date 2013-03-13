package com.cc.addressbook.presenters;

import com.cc.addressbook.views.ShowAllContactsView;

/**
 * @author cclaudiu
 */

public class DeleteContactPresenter implements ShowAllContactsView.DeleteContactOneByOneListener {

    private final ShowAllContactsView showAllContactsView;

    public DeleteContactPresenter(ShowAllContactsView showAllContactsView) {
        this.showAllContactsView = showAllContactsView;

        showAllContactsView.addPresenter(this);
    }

    @Override
    public void deleteContact() {
        showAllContactsView.deleteContactOneByOne();
    }
}