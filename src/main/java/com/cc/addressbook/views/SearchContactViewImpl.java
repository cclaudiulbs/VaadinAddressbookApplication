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

    private TextField firstNameSearchField = new TextField("First Name");
    private TextField lastNameSearchField = new TextField("Last Name");
    private TextField phoneNumberSearchField = new TextField("Mobile Phone Number");
    private Button searchButton = new Button("Search Contact");
    private Button clearButton = new Button("Clear Search");

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
        firstNameSearchField.setValue("");
        lastNameSearchField.setValue("");
        phoneNumberSearchField.setValue("");
    }

    private ComponentContainer buildSearchContactLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(Boolean.TRUE);
        mainLayout.setMargin(Boolean.TRUE);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponent(searchButton);
        buttonsLayout.addComponent(clearButton);
        buttonsLayout.setSpacing(Boolean.TRUE);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setSpacing(Boolean.TRUE);
        searchLayout.setSizeUndefined();

        searchLayout.addComponent(firstNameSearchField);
        searchLayout.addComponent(lastNameSearchField);
        searchLayout.addComponent(phoneNumberSearchField);

        mainLayout.addComponent(searchLayout);
        mainLayout.addComponent(buttonsLayout);
        mainLayout.setComponentAlignment(searchLayout, Alignment.TOP_LEFT);
        mainLayout.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_LEFT);

        searchButton.addListener(this);
        clearButton.addListener(this);

        return mainLayout;
    }

    /**
     * When the user clicks the Search Button, the presenter listening to this
     * event is notified from within this View and the Logic: of
     * "addContacts(filtered contacts, BuilderPatternDemo is called)" from the
     * SearchContactPresenter
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

                ContactNotificationUtil.showStandardNotification("Searching contact", mainAppInstance);

            } else if (clickEvent.getButton() == clearButton) {
                searchPresenter.clearSearchForm();

                ContactNotificationUtil.showStandardNotification("Cleared Searched Form", mainAppInstance);
            }
        }
    }

    // ---------- Presenter should NOT know what type of components the view has!-------------//
    private SearchCriteria parseSearchForm() {
        String searchFirstName = firstNameSearchField.getValue().toString();
        String lastNameSearch = lastNameSearchField.getValue().toString();
        String phoneNumSearch = phoneNumberSearchField.getValue().toString();

        SearchCriteria criteria = new SearchCriteria.Builder().firstName(searchFirstName).lastName(lastNameSearch).phoneNumber(phoneNumSearch).build();

        return criteria;
    }
}