package com.cc.addressbook.presenters;

import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.CustomerModel;
import com.cc.addressbook.views.CustomerTableView;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author cclaudiu
 *
 */

public class CustomerTablePresenter implements CustomerTableView.CustomerTableViewListener {

    private CustomerModel customerModel;
    private CustomerTableView customerView;

    public CustomerTablePresenter(CustomerModel model, CustomerTableView view) {
        this.customerModel = model;
        this.customerView = view;

        // register itself as a Listener for the Event
        customerView.addListener(this);
    }

    @Override
    public void populateContainer(BeanItemContainer<PersonEntity> container) {
        for(PersonEntity eachPerson : customerModel.getCustomers()) {
            container.addBean(eachPerson);
        }
    }
}