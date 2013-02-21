package com.cc.addressbook.views;

import com.cc.addressbook.constants.CustomerEditViewOperations;
import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 *
 */

public interface CustomerEditView extends Component {

    void setDisplay(ComponentContainer customerEditView, PersonEntity customer);

    public interface CustomerEditViewListener {
        void changeCustomer(PersonEntity customer, CustomerEditViewOperations operationTypes);
    }

    void addListener(CustomerEditViewListener customerEditListener);

    ComponentContainer getSaveView();
    ComponentContainer getClearView();
    ComponentContainer getCancelView();
}