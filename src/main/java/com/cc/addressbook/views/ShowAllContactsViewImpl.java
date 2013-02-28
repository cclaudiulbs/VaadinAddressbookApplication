package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.entities.TableEntity;
import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author cclaudiu
 *
 * Pure Vaadin UI Implementation
 */

public class ShowAllContactsViewImpl<T extends TableEntity>
        extends CustomComponent 
        implements ShowAllContactsView<T>, ItemClickEvent.ItemClickListener {

	private static final long serialVersionUID = 1L;
	
	private final List<ShowAllContactsListener> listeners = new ArrayList<>();
    private final BeanItemContainer<T> contactsContainer = new BeanItemContainer<>(TableEntity.class);
	private List<T> contacts = new ArrayList<>();

    public ShowAllContactsViewImpl() {
        buildShowContactsLayout();
    }

    @Override
    public void addListener(ShowAllContactsListener listener) {
        listeners.add(listener);
    }
    
	@Override
	public void addContacts(List<T> contacts) {
		this.contacts.addAll(contacts);
		contactsContainer.addAll(contacts);
	}
	
	@Override public List<T> getContactsList() {
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
        
        PagedTable contactsTable = new PagedTable();
        contactsTable.setSelectable(Boolean.TRUE);
        contactsTable.setNullSelectionAllowed(Boolean.FALSE);
        contactsTable.setMultiSelect(Boolean.FALSE);
        contactsTable.setImmediate(Boolean.TRUE);
        contactsTable.setWriteThrough(Boolean.FALSE);
        contactsTable.setPageLength(10);
        contactsTable.setSizeFull();

        List<Object> visibleColumnIds = new ArrayList<>();
        List<String> visibleColumnLables = new ArrayList<>();
        formatTableHeader(visibleColumnIds, visibleColumnLables);

        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(visibleColumnIds.toArray());
        contactsTable.setColumnHeaders(visibleColumnLables.toArray(new String[]{}));
        contactsTable.setPageLength(10);
        contactsTable.setImmediate(Boolean.TRUE);

        mainLayout.addComponent(contactsTable);
        mainLayout.addComponent(contactsTable.createControls());
        
        setCompositionRoot(mainLayout);
    }

	private void formatTableHeader(List<Object> visibleColumnIds, List<String> visibleColumnLables) {
		visibleColumnIds.add("firstName");
        visibleColumnIds.add("lastName");
        visibleColumnIds.add("phoneMobileNumber");
        visibleColumnIds.add("phoneHomeNumber");
        visibleColumnIds.add("email");
        visibleColumnIds.add("age");
        visibleColumnIds.add("address");

        visibleColumnLables.add("First Name");
        visibleColumnLables.add("Last Name");
        visibleColumnLables.add("Mobile Phone NO");
        visibleColumnLables.add("Home Phone NO");
        visibleColumnLables.add("Email");
        visibleColumnLables.add("Age");
        visibleColumnLables.add("Address");
	}
}