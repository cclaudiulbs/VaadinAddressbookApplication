package com.cc.addressbook.views;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class AddContactViewImpl extends CustomComponent implements AddContactView {

	private static final long serialVersionUID = 1L;

	public AddContactViewImpl() {
		setCompositionRoot(new Label("AddContactViewImpl"));
	}
}
