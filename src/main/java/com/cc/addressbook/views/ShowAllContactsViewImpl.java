package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.entities.PersonEntity;
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

public class ShowAllContactsViewImpl
        extends CustomComponent 
        implements ShowAllContactsView, ItemClickEvent.ItemClickListener {

	private static final long serialVersionUID = 1L;
	
	private final List<ShowAllContactsListener> listeners = new ArrayList<>();
    private final BeanItemContainer<PersonEntity> contactsContainer = new BeanItemContainer<>(PersonEntity.class);

    public ShowAllContactsViewImpl() {
        buildShowContactsLayout();
    }

    @Override
    public void addListener(ShowAllContactsListener listener) {
        listeners.add(listener);
    }

    @Override
    public BeanItemContainer<PersonEntity> getContactsContainer() {
        return contactsContainer;
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        for(ShowAllContactsListener eachListener : listeners) {
            eachListener.populateContainer(contactsContainer);
        }
    }

    private void buildShowContactsLayout() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        
        PagedTable contactsTable = new PagedTable();
        contactsTable.setSizeFull();
        contactsTable.setSelectable(Boolean.TRUE);
        contactsTable.setNullSelectionAllowed(Boolean.FALSE);
        contactsTable.setMultiSelect(Boolean.FALSE);
        contactsTable.setImmediate(Boolean.TRUE);
        contactsTable.setWriteThrough(Boolean.FALSE);
        contactsTable.setPageLength(30);

        List<Object> visibleColumnIds = new ArrayList<>();
        visibleColumnIds.add("firstName");
        visibleColumnIds.add("lastName");
        visibleColumnIds.add("phoneMobileNumber");
        visibleColumnIds.add("phoneHomeNumber");
        visibleColumnIds.add("email");
        visibleColumnIds.add("age");
        visibleColumnIds.add("address");

        List<String> visibleColumnLables = new ArrayList<>();
        visibleColumnLables.add("First Name");
        visibleColumnLables.add("Last Name");
        visibleColumnLables.add("Mobile Phone NO");
        visibleColumnLables.add("Home Phone NO");
        visibleColumnLables.add("Email");
        visibleColumnLables.add("Age");
        visibleColumnLables.add("Address");

        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(visibleColumnIds.toArray());
        contactsTable.setColumnHeaders(visibleColumnLables.toArray(new String[]{}));
        contactsTable.setPageLength(10);
        contactsTable.setImmediate(Boolean.TRUE);

        mainLayout.addComponent(contactsTable);
        mainLayout.addComponent(contactsTable.createControls());
        
        setCompositionRoot(mainLayout);
    }
}