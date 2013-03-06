package com.cc.addressbook.views;

import com.vaadin.Application;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ShareContactViewImpl extends CustomComponent implements ShareContactView {

	private static final long serialVersionUID = 1L;

	public ShareContactViewImpl(Application mainAppInstance) {
		setCompositionRoot(new Label("ShareContactViewImpl"));
	}
}
