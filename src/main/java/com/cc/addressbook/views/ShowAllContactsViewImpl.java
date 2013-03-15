package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.cc.addressbook.util.DeleteColumnGenerator;
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
        implements ShowAllContactsView, ItemClickEvent.ItemClickListener, Button.ClickListener {

    private static final long serialVersionUID = 1L;

    private final List<ShowContactsListener> showAllContactListeners;
    private final List<DeleteContactOneByOneListener> deleteContactListeners;
    private final BeanItemContainer<PersonEntity> contactsContainer;
    private final Application mainAppInstance;
    private List<PersonEntity> contacts;
    private final Table contactsTable;
    private Object selectedItemId;
    private DeleteColumnGenerator deleteColumnGenerator;
    private boolean isDeleteColumnVisible;

    public ShowAllContactsViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        this.showAllContactListeners = new ArrayList<>();
        this.deleteContactListeners = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.contactsContainer = new BeanItemContainer<PersonEntity>(PersonEntity.class);
        this.contactsTable = new Table();
        this.deleteColumnGenerator = new DeleteColumnGenerator(this);

        buildShowContactsLayout();
    }

    /**
     * Register this Show Listener
     *
     * @param showContactslistener
     */
    @Override
    public void addPresenter(ShowContactsListener showContactslistener) {
        showAllContactListeners.add(showContactslistener);
    }

    /**
     * Register also the Delete Listener
     *
     * @param deleteContactsListener
     */
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

    /**
     * The delete method works only if there's an already selected Table Item, which is
     * set by listening to Item.ClickEvent; The ShowAllContactsViewImpl is a Item.ClickListener
     * as a widget-listener, and stores the selected Selected-ItemId into a global Variable
     */
    @Override
    public void deleteContactOneByOne() {
        if (selectedItemId != null) {
            contactsTable.getContainerDataSource().removeItem(selectedItemId);
        }
    }

    /**
     * The checkDeleteColumnAppearance should be valid for the event to work,(that is if 0 then create it) otherwise
     * an IllegalArgumentException is thrown, since the column is already generated
     */
    @Override
    public void displayDeleteOptions() {        // mark this event and do not add twice the same column: eg: when the user clicks the delete button this is generated, when the user clicks show-only remove the generated column
        if (!isDeleteColumnVisible) {
            contactsTable.addGeneratedColumn("Delete Contact", deleteColumnGenerator);
            isDeleteColumnVisible = true;
        }
    }

    /**
     * The checkDeleteColumnAppearance should be valid for the event to work,(that is if > 0 then remove it) otherwise
     * an IllegalArgumentException is thrown, since the column is already removed
     */
    @Override
    public void removeDeleteOptions() {
        if(isDeleteColumnVisible) {
            contactsTable.removeGeneratedColumn(deleteColumnGenerator.getLazyGeneratedColumnId());
            isDeleteColumnVisible = false;
        }
    }

    /**
     * When this Button.ClickEvent is fired, on the Delete Contact Generated Column the "this" instance works as a
     * widget-listener
     *
     * @param clickEvent
     */
    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        for (DeleteContactOneByOneListener deleteContactListener : deleteContactListeners) {
            deleteContactListener.deleteContact();
        }
    }

    /**
     * Each ItemClickEvent corresponding to Table UI Component -- identify which Item from Table was currently selected
     */
    @Override
    public void itemClick(ItemClickEvent event) {
        selectedItemId = event.getItemId();
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

        contactsTable.addListener((ItemClickEvent.ItemClickListener) this);

        final List<Object> visibleColumnIds = new ArrayList<>();
        final List<String> visibleColumnLabels = new ArrayList<>();
        setContactTableHeader(visibleColumnIds, visibleColumnLabels);

        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(visibleColumnIds.toArray());
        contactsTable.setColumnHeaders(visibleColumnLabels.toArray(new String[]{}));

        mainLayout.addComponent(contactsTable);
//        mainLayout.addComponent(contactsTable.createControls());      --->>> Used by PagedTable

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

//    Other approach to Generate a New Table-widget-column dynamically
//    Rather than working with Anonymous Inner Listeners -- we choosed the MVP approach that is the Presenter/Listener
//    is in charge of populating the View, the View just implement this behavior; also the Presenter does NOT have a
//    clear image of the components with which the View works;
//    However when we're fast we can code this approach which seems very natural but, with large amount of code
//    becomes blurry :)
//    So, in order to generate dynamically a new column to a Table(which is NOT added to the container) but
//    lets us add particular Vaadin UI widgets to the table this approach can do the job:

//    @Override
//    public void deleteContactOneByOne() {
//        contactsTable.addGeneratedColumn("Delete Contact", new Table.ColumnGenerator() {
//
//            @Override
//            public Object generateCell(final Table source, final Object itemId, Object columnId) {
//                final Button deleteContact = new Button("Delete");
//                deleteContact.addListener(new Button.ClickListener() {
//
//                    @Override
//                    public void buttonClick(Button.ClickEvent clickEvent) {
//                        source.getContainerDataSource().removeItem(itemId);
//                    }
//                });
//
//                return deleteContact;
//            }
//        });
//    }

}