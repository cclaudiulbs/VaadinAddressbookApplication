package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.constants.CustomerEditViewOperations;
import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 * @author cclaudiu
 *
 */

public class CustomerEditViewImpl extends CustomComponent implements CustomerEditView, Button.ClickListener {

	private static final long serialVersionUID = 1L;

	private final List<CustomerEditViewListener> listeners = new ArrayList<>();

    private TextField firstName = new TextField("FirstName");
    private TextField lastName = new TextField("LastName");
    private TextField mobilePhone = new TextField("Phone");
    private TextField homePhone = new TextField("Home Phone");
    private TextField city = new TextField("City");
    private TextField age = new TextField("Age");
    private TextField address = new TextField("Address");
    private TextField email = new TextField("Email");
    private Button editButton = new Button("Edit");
    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");
    private Button resetButton = new Button("Reset");

    public CustomerEditViewImpl() {
        buildCustomerEditView();
    }

    @Override
    public void setDisplay(ComponentContainer customerEditView, PersonEntity customer) {
        setDisplay(customerEditView, customer);
    }

    @Override
    public void addListener(CustomerEditViewListener customerEditListener) {
        listeners.add(customerEditListener);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        for(CustomerEditViewListener eachListener : listeners) {
            PersonEntity persistedCustomer = new PersonEntity();
            persistedCustomer.setFirstName(String.valueOf(firstName.getValue()));
            persistedCustomer.setFirstName(String.valueOf(lastName.getValue()));
            persistedCustomer.setFirstName(String.valueOf(address.getValue()));
            persistedCustomer.setFirstName(String.valueOf(age.getValue()));
            persistedCustomer.setFirstName(String.valueOf(mobilePhone.getValue()));
            persistedCustomer.setFirstName(String.valueOf(homePhone.getValue()));
            persistedCustomer.setFirstName(String.valueOf(city.getValue()));
            persistedCustomer.setFirstName(String.valueOf(email.getValue()));

            eachListener.changeCustomer(persistedCustomer,
                    CustomerEditViewOperations.valueOf(String.valueOf(event.getButton().getValue())));
        }
    }

    private void buildCustomerEditView() {

        setSizeFull();

        final FormLayout mainFormLayout = new FormLayout();
        mainFormLayout.setSizeFull();

        firstName = new TextField("FirstName");
        lastName = new TextField("LastName");
        mobilePhone = new TextField("Phone");
        homePhone = new TextField("Home Phone");
        city = new TextField("City");
        age = new TextField("Age");
        address = new TextField("Address");
        email = new TextField("Email");

        editButton = new Button("Edit");
        saveButton = new Button("Save");
        resetButton = new Button("Reset");
        cancelButton = new Button("Cancel");

        editButton.addListener(this);
        saveButton.addListener(this);
        resetButton.addListener(this);
        cancelButton.addListener(this);

        mainFormLayout.addComponent(firstName);
        mainFormLayout.addComponent(lastName);
        mainFormLayout.addComponent(mobilePhone);
        mainFormLayout.addComponent(homePhone);
        mainFormLayout.addComponent(city);
        mainFormLayout.addComponent(age);
        mainFormLayout.addComponent(email);
        mainFormLayout.addComponent(address);
        mainFormLayout.addComponent(editButton);
        mainFormLayout.addComponent(saveButton);
        mainFormLayout.addComponent(resetButton);
        mainFormLayout.addComponent(cancelButton);

        mainFormLayout.setSpacing(Boolean.TRUE);
        mainFormLayout.setMargin(Boolean.TRUE);

        setCompositionRoot(mainFormLayout);

    }

    // if readonly then disable some buttons from the menu

    @Override
    public FormLayout getSaveView() {

        return null;
    }

    @Override
    public FormLayout getClearView() {

        return null;
    }

    @Override
    public FormLayout getCancelView() {

        return null;
    }

}