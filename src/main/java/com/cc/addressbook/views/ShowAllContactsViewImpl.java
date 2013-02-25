package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * @author cclaudiu
 *
 * Pure Vaadin UI Implementation
 */

public class ShowAllContactsViewImpl
        extends CustomComponent implements ShowAllContactsView, ItemClickEvent.ItemClickListener {

	private static final long serialVersionUID = 1L;
	
	private final List<ShowAllContactsListener> listeners = new ArrayList<>();
    private final BeanItemContainer<PersonEntity> customerContainer = new BeanItemContainer<>(PersonEntity.class);
    private final VerticalSplitPanel mainSplittedLayout = new VerticalSplitPanel();

    public ShowAllContactsViewImpl() {
        buildCustomerTableLayout();
    }

    @Override
    public void addListener(ShowAllContactsListener listener) {
        listeners.add(listener);
    }

    @Override
    public BeanItemContainer<PersonEntity> getPersonContainer() {
        return customerContainer;
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        for(ShowAllContactsListener eachListener : listeners) {
            eachListener.populateContainer(this.customerContainer);
        }
    }

    private void buildCustomerTableLayout() {
        setSizeFull();
        mainSplittedLayout.setSizeFull();

        Table customerTable = new Table();
        customerTable.setSizeFull();
        customerTable.setSelectable(Boolean.TRUE);
        customerTable.setNullSelectionAllowed(Boolean.FALSE);
        customerTable.setMultiSelect(Boolean.FALSE);
        customerTable.setImmediate(Boolean.TRUE);

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

        customerTable.setContainerDataSource(customerContainer);
        customerTable.setVisibleColumns(visibleColumnIds.toArray());
        customerTable.setColumnHeaders(visibleColumnLables.toArray(new String[]{}));
        customerTable.setPageLength(10);
        customerTable.setImmediate(Boolean.TRUE);

        mainSplittedLayout.setFirstComponent(customerTable);

        setCompositionRoot(mainSplittedLayout);

    }
}