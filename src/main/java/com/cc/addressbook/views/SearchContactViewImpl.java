package com.cc.addressbook.views;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class SearchContactViewImpl extends CustomComponent implements SearchContactView {

	private static final long serialVersionUID = 1L;
	
	public SearchContactViewImpl() {
		setCompositionRoot(new Label("OK!"));
	}

}