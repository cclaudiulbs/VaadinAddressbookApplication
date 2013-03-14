package com.cc.addressbook.presenters;

import com.cc.addressbook.views.ShowAllContactsView;

/**
 * @author cclaudiu
 *         <br/>
 *         <p/>
 *         Listen to Delete Contact Events generated from the ShowAllContactsView view
 */

public class DeleteContactPresenter implements ShowAllContactsView.DeleteContactOneByOneListener {

    private final ShowAllContactsView showAllContactsView;

    public DeleteContactPresenter(ShowAllContactsView showAllContactsView) {
        this.showAllContactsView = showAllContactsView;

        // ------- register itself to delete-events ---------//
        showAllContactsView.addPresenter(this);
    }

    @Override
    public void deleteContact() {
        showAllContactsView.deleteContactOneByOne();
    }
}