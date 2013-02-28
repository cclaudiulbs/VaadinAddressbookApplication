package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class SearchContactViewImpl extends CustomComponent implements SearchContactView {

	private static final long serialVersionUID = 1L;
	List<SearchContactListener> presenters = new ArrayList<>();
	
	public SearchContactViewImpl() {
		setCompositionRoot(new Label("OK!"));
	}

	@Override
	public void addPresenter(SearchContactListener presenter) {
		presenters.add(presenter);
	}

}