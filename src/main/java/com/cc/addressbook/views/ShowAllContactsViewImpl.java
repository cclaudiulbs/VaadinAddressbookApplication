package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *         <p/>
 *         Pure Vaadin UI Implementation
 */

public class ShowAllContactsViewImpl
        extends CustomComponent
        implements ShowAllContactsView, ItemClickEvent.ItemClickListener {

    private static final long serialVersionUID = 1L;

    private final List<ShowAllContactsListener> listeners;
    private final BeanItemContainer<PersonEntity> contactsContainer;
    private final Application mainAppInstance;
    private List<PersonEntity> contacts;

    public ShowAllContactsViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        this.listeners = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.contactsContainer = new BeanItemContainer<PersonEntity>(PersonEntity.class);

        buildShowContactsLayout();
    }

    @Override
    public void addPresenter(ShowAllContactsListener listener) {
        listeners.add(listener);
    }

    @Override
    public void addContacts(List<PersonEntity> contacts) {
        contacts.addAll(contacts);

        if (contactsContainer.removeAllItems()) {
            contactsContainer.addAll(contacts);
        }
    }

    @Override
    public List<PersonEntity> getContactsList() {
        ContactNotificationUtil.prompt(contacts.size() + " contacts have been retrieved from database!", mainAppInstance);
        return contacts;
    }

    /**
     * Each ItemClickEvent corresponding to Tree UI Component, delegate it to the ShowContactsPresenter
     * which will populate the List with Contacts from Model, and add all to the container
     */
    @Override
    public void itemClick(ItemClickEvent event) {

    }

    private void buildShowContactsLayout() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

//        PagedTable contactsTable = new PagedTable();
        Table contactsTable = new Table();
        contactsTable.setSelectable(Boolean.TRUE);
        contactsTable.setNullSelectionAllowed(Boolean.FALSE);
        contactsTable.setMultiSelect(Boolean.FALSE);
        contactsTable.setImmediate(Boolean.TRUE);
        contactsTable.setWriteThrough(Boolean.FALSE);
//        contactsTable.setPageLength(10);
        contactsTable.setSizeFull();

        List<Object> visibleColumnIds = new ArrayList<>();
        List<String> visibleColumnLabels = new ArrayList<>();
        setContactTableHeader(visibleColumnIds, visibleColumnLabels);

        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(visibleColumnIds.toArray());
        contactsTable.setColumnHeaders(visibleColumnLabels.toArray(new String[]{}));

        mainLayout.addComponent(contactsTable);
//        mainLayout.addComponent(contactsTable.createControls());

        // let the Table take as much space as it needs and shrink the parent-layout according to
        mainLayout.setExpandRatio(contactsTable, 1);
        setCompositionRoot(mainLayout);
    }

    private void setContactTableHeader(List<Object> visibleColumnIds, List<String> visibleColumnLabels) {
        visibleColumnIds.add("firstName");
        visibleColumnIds.add("lastName");
        visibleColumnIds.add("phoneMobileNumber");
        visibleColumnIds.add("phoneHomeNumber");
        visibleColumnIds.add("email");
        visibleColumnIds.add("age");
        visibleColumnIds.add("address");

        visibleColumnLabels.add("First Name");
        visibleColumnLabels.add("Last Name");
        visibleColumnLabels.add("Mobile Phone NO");
        visibleColumnLabels.add("Home Phone NO");
        visibleColumnLabels.add("Email");
        visibleColumnLabels.add("Age");
        visibleColumnLabels.add("Address");
    }
}