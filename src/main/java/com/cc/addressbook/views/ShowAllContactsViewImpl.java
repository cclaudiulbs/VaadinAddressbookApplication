package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
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

    private final List<ShowContactsListener> showAllContactListeners;
    private final List<DeleteContactOneByOneListener> deleteContactListeners;
    private final BeanItemContainer<PersonEntity> contactsContainer;
    private final Application mainAppInstance;
    private List<PersonEntity> contacts;
    private final Table contactsTable;


    public ShowAllContactsViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        this.showAllContactListeners = new ArrayList<>();
        this.deleteContactListeners = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.contactsContainer = new BeanItemContainer<PersonEntity>(PersonEntity.class);
        this.contactsTable = new Table();

        buildShowContactsLayout();
    }

    /**
     * Register both Type of Listeners to the this View -- has 2 TYpe of Listeners: Show && Delete
     *
     * @param showContactslistener
     */
    @Override
    public void addPresenter(ShowContactsListener showContactslistener) {
        showAllContactListeners.add(showContactslistener);
    }

    @Override
    public void addPresenter(DeleteContactOneByOneListener deleteContactsListener) {
        deleteContactListeners.add(deleteContactsListener);
    }

    @Override
    public void addContacts(List<PersonEntity> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);

        if (contactsContainer.removeAllItems()) {
            contactsContainer.addAll(contacts);
        }

        ContactNotificationUtil.showTrayNotification((this.contacts.isEmpty() ? "No" : this.contacts.size()) + " contacts have been retrieved from database!", mainAppInstance);
    }

    @Override
    public List<PersonEntity> getContactsList() {
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
        contactsTable.setSelectable(Boolean.TRUE);
        contactsTable.setNullSelectionAllowed(Boolean.FALSE);
        contactsTable.setMultiSelect(Boolean.FALSE);
        contactsTable.setImmediate(Boolean.TRUE);
        contactsTable.setWriteThrough(Boolean.FALSE);
//        contactsTable.setPageLength(10);
        contactsTable.setSizeFull();

        final List<Object> visibleColumnIds = new ArrayList<>();
        final List<String> visibleColumnLabels = new ArrayList<>();
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

    // TODO: extract this method into a Class, and based on this "generated" Button, when clicked the Delete Presenter is called to do the action
    @Override
    public void deleteContactOneByOne() {
        contactsTable.addGeneratedColumn("Delete Contact", new Table.ColumnGenerator() {

            @Override
            public Object generateCell(final Table source, final Object itemId, Object columnId) {
                final Button deleteContact = new Button("Delete");
                deleteContact.addListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        source.getContainerDataSource().removeItem(itemId);
                    }
                });

                return deleteContact;
            }
        });
    }

    // TO be called by presenter
//    @Override
    public void deleteContactOneByOneClaudiu() {
        contactsTable.addGeneratedColumn("Delete Contact", new Table.ColumnGenerator() {

            @Override
            public Object generateCell(final Table source, final Object itemId, Object columnId) {
                final Button deleteContact = new Button("Delete");
                deleteContact.addListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        source.getContainerDataSource().removeItem(itemId);
                    }
                });

                return deleteContact;
            }
        });
    }


    private void deleteContactToBeIncludedInView(Object itemId) {
        contactsTable.getContainerDataSource().removeItem(itemId);
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