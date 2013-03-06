package com.cc.addressbook.views;

import com.vaadin.Application;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class AddContactViewImpl extends CustomComponent
        implements AddContactView, ClickListener {

    private static final long serialVersionUID = 1L;

    private Application mainAppInstance;

    public AddContactViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        setCompositionRoot(new Label("AddContactViewImpl"));
    }

    @Override
    public void buttonClick(ClickEvent event) {

    }
}
