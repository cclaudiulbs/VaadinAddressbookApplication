package com.cc.addressbook.views;

import com.vaadin.Application;
import com.vaadin.ui.CustomComponent;

public class EditContactsViewImpl extends CustomComponent implements EditContactsView {

    private Application mainAppInstance;

    public EditContactsViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void addListener() {
        // TODO Auto-generated method stub

    }

}
