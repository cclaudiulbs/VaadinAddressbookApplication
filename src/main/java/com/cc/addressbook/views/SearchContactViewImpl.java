package com.cc.addressbook.views;

import com.cc.addressbook.util.ContactNotificationUtil;
import com.cc.addressbook.util.SearchCriteria;
import com.vaadin.Application;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *         <p/>
 *         After displaying this Search View, the Filter Presenter'
 *         responsability is triggered
 */

public class SearchContactViewImpl extends CustomComponent implements SearchContactView, Button.ClickListener {

    private static final long serialVersionUID = 1L;

    private Application mainAppInstance;

    private List<SearchContactListener> searchPresenters = new ArrayList<>();

    private FormLayout searchForm = new FormLayout();
    private Button searchButton = new Button("Search Contact");
    private Button clearButton = new Button("Clear Search");
    private Button cancelButton = new Button("Cancel Operation");

    public SearchContactViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        setCompositionRoot(buildSearchContactLayout());
    }

    // ------------- Application Logic Event/Presenter Model ----------------//
    @Override
    public void addPresenter(SearchContactListener presenter) {
        searchPresenters.add(presenter);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return parseSearchForm();
    }

    @Override
    public void clearSearchFormEvent() {
        ((TextField) searchForm.getComponent(0)).setValue(" ");
        ((TextField) searchForm.getComponent(1)).setValue(" ");
        ((TextField) searchForm.getComponent(2)).setValue(" ");
    }

    private ComponentContainer buildSearchContactLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(Boolean.TRUE);

        TextField firstNameSearch = new TextField("First Name");
        TextField lastNameSearch = new TextField("Last Name");
        TextField phoneNumberSearch = new TextField("Mobile Phone Number");

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponent(searchButton);
        buttonsLayout.addComponent(clearButton);
        buttonsLayout.addComponent(cancelButton);
        buttonsLayout.setSpacing(Boolean.TRUE);

        searchForm.setSpacing(Boolean.TRUE);
        searchForm.setSizeFull();

        searchForm.addComponent(firstNameSearch, 0);
        searchForm.addComponent(lastNameSearch, 1);
        searchForm.addComponent(phoneNumberSearch, 2);
        searchForm.addComponent(buttonsLayout);

        searchForm.setComponentAlignment(firstNameSearch, Alignment.TOP_CENTER);
        searchForm.setComponentAlignment(lastNameSearch, Alignment.MIDDLE_CENTER);
        searchForm.setComponentAlignment(phoneNumberSearch, Alignment.BOTTOM_CENTER);
        searchForm.setComponentAlignment(buttonsLayout, Alignment.TOP_LEFT);

        mainLayout.addComponent(searchForm);
        mainLayout.setComponentAlignment(searchForm, Alignment.TOP_LEFT);

        searchButton.addListener(this);
        cancelButton.addListener(this);

        return mainLayout;
    }

    /*
     * When the user clicks the Search Button, the presenter listening to this
     * event is notified from within this View and the Logic: of
     * "addContacts(filtered contacts, SearchCriteria is called)" from the
     * SearchContactFilterPresenter
     *
     * The Navigation-Application Controller dispatches only to the
     * SearchContactView It is the SearchContactView, which is a plain Vaadin
     * implementation to delegate to another particular Presenter for Search &&
     * Filter Contacts && display the results via the "addContact()" method
     */
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        for (SearchContactListener searchPresenter : searchPresenters) {
            if (clickEvent.getButton() == searchButton) {
                searchPresenter.searchContact();
                ContactNotificationUtil.prompt("Searching contact", mainAppInstance);

            } else if (clickEvent.getButton() == cancelButton) {
                //TODO: design the UX Interaction(what the user wants to see)

            } else if (clickEvent.getButton() == clearButton) {
                searchPresenter.clearSearchForm();
                ContactNotificationUtil.prompt("Cleared Searched Form", mainAppInstance);
            }
        }
    }

    // ---------- Presenter should NOT know what type of components the view has!-------------//
    private SearchCriteria parseSearchForm() {
        String searchFirstName = getSearchedField(searchForm, 0);
        String lastNameSearch = getSearchedField(searchForm, 1);
        String phoneNumSearch = getSearchedField(searchForm, 2);

        SearchCriteria criteria = new SearchCriteria.Builder().firstName(searchFirstName).lastName(lastNameSearch).phoneNumber(phoneNumSearch).build();
        return criteria;
    }

    private String getSearchedField(AbstractOrderedLayout searchForm, int idx) {
        return ((TextField) searchForm.getComponent(idx)).getValue().toString();
    }
}