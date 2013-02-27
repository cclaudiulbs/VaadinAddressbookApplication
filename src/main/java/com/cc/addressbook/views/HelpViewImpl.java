package com.cc.addressbook.views;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class HelpViewImpl extends CustomComponent implements HelpView {

	private static final long serialVersionUID = 1L;

	public HelpViewImpl() {
		setCompositionRoot(new Label("HelpViewImpl"));
	}
}
