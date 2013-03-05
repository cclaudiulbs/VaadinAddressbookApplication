package com.cc.addressbook.views;

import com.cc.addressbook.util.SearchCriteria;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *
 * After displaying this Search View, the Filter Presenter' responsability is triggered
 */

public class SearchContactViewImpl extends CustomComponent implements SearchContactView, Button.ClickListener {

    private static final long serialVersionUID = 1L;

    private List<SearchContactListener> presenters = new ArrayList<>();

    private FormLayout searchForm = new FormLayout();
    private Button searchButton = new Button("Search Contact");
    private Button cancelButton = new Button("Cancel Operation");

    public SearchContactViewImpl() {
        setCompositionRoot(buildSearchContactLayout());
    }

    // ------------- Application Logic Event/Presenter Model ----------------//
    @Override
    public void addPresenter(SearchContactListener presenter) {
        presenters.add(presenter);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return parseSearchForm();
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

        searchButton.addListener(this);
        cancelButton.addListener(this);

        searchForm.addComponent(searchButton);
        searchForm.addComponent(cancelButton);

        mainLayout.addComponent(searchForm);

        return mainLayout;
    }

    /*
     * When the user clicks the Search Button, the presenter listening to this event is notified from within this View
     * and the Logic: of "addContacts(filtered contacts, SearchCriteria is called)" from the SearchContactFilterPresenter
     *
     * The Navigation-Application Controller dispatches only to the SearchContactView
     * It is the SearchContactView, which is a plain Vaadin implementation to delegate to another particular
     * Presenter for Search && Filter Contacts && display the results via the "addContact()" method
     */
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if(clickEvent.getButton() == searchButton) {
            for (SearchContactListener searchPresenter : presenters) {
                searchPresenter.searchContact();
            }
        } else if(clickEvent.getButton() == cancelButton) {
            // TODO: implement the Logic for cancel the operation --> first design the UX Interaction(what the user wants to see)
        }
    }

    //---------- Presenter should NOT know what type of components the view has! --------------//
    private SearchCriteria parseSearchForm() {
        String searchFirstName = getSearchedField(searchForm, 0);
        String lastNameSearch = getSearchedField(searchForm, 1);
        String phoneNumSearch = getSearchedField(searchForm, 2);

        SearchCriteria criteria = new SearchCriteria.Builder()
                .firstName(searchFirstName)
                .lastName(lastNameSearch)
                .phoneNumber(phoneNumSearch)
                .build();
        return criteria;
    }

    private String getSearchedField(AbstractOrderedLayout searchForm, int idx) {
        return ((TextField) searchForm.getComponent(idx)).getValue().toString();
    }
}