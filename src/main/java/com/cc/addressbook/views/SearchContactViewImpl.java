package com.cc.addressbook.views;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class SearchContactViewImpl extends CustomComponent implements SearchContactView {

	private static final long serialVersionUID = 1L;
	private List<SearchContactListener> presenters = new ArrayList<>();
    private FormLayout searchForm = new FormLayout();

    public SearchContactViewImpl() {
        setCompositionRoot(buildSearchContactLayout());
	}

	@Override
	public void addPresenter(SearchContactListener presenter) {
		presenters.add(presenter);
	}

    @Override
    public AbstractOrderedLayout getSearchForm() {
        return searchForm;
    }

    private ComponentContainer buildSearchContactLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        TextField firstNameSearch = new TextField("First Name");
        TextField lastNameSearch = new TextField("Last Name");
        TextField phoneNumberSearch = new TextField("Mobile Phone Number");

        searchForm.setSpacing(Boolean.TRUE);
        searchForm.setSizeFull();

        searchForm.addComponent(firstNameSearch, 0);
        searchForm.addComponent(lastNameSearch, 1);
        searchForm.addComponent(phoneNumberSearch, 2);

        searchForm.setComponentAlignment(firstNameSearch, Alignment.TOP_LEFT);
        searchForm.setComponentAlignment(lastNameSearch, Alignment.MIDDLE_LEFT);
        searchForm.setComponentAlignment(phoneNumberSearch, Alignment.BOTTOM_LEFT);

        Button searchButton = new Button("Search Contact");
        Button cancelButton = new Button("Cancel Operation");

        searchForm.addComponent(searchButton);
        searchForm.addComponent(cancelButton);

        mainLayout.addComponent(searchForm);

        return mainLayout;
    }
}