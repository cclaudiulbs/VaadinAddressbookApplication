package com.cc.addressbook.views;

import com.cc.addressbook.util.AddressbookConvertor;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class AddContactViewImpl extends CustomComponent
        implements AddContactView, ClickListener {

    private static final long serialVersionUID = 1L;
    private Application mainAppInstance;
    private List<AddContactListener> addContactListeners = new ArrayList<>();

    private final Button addButton = new Button("Add");
    private final Button clearButton = new Button("Reset");
    private final Button cancelButton = new Button("Cancel");
    private final TextField firstNameField = new TextField("FirstName");
    private final TextField lastNameField = new TextField("LastName");
    private final TextField addressField = new TextField("Address");
    private final TextField emailField = new TextField("Email");
    private final TextField jobTitleField = new TextField("Job Title");
    private final TextField jobDescField = new TextField("Job Description");
    private final TextField ageField = new TextField("Age");
    private final TextField mobilePhoneField = new TextField("Mobile Phone");
    private final TextField homePhoneField = new TextField("Home Phone");

    public AddContactViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;

        setCompositionRoot(getAddContactMainLayout());
    }

    @Override
    public void addListener(AddContactListener addContactListener) {
        addContactListeners.add(addContactListener);
    }

    @Override
    public void reset() {
        firstNameField.setValue("");
        lastNameField.setValue("");
        addressField.setValue("");
        emailField.setValue("");
        jobTitleField.setValue("");
        jobDescField.setValue("");
        ageField.setValue("");
        mobilePhoneField.setValue("");
        homePhoneField.setValue("");
    }

    @Override
    public void buttonClick(ClickEvent clickEvent) {

        for (AddContactListener eachPresenter : addContactListeners) {
            if (clickEvent.getButton() == addButton) {
                eachPresenter.addContact(AddressbookConvertor.fromAddContactFormToPerson(firstNameField, lastNameField,
                        addressField, emailField,
                        jobTitleField, jobDescField,
                        mobilePhoneField, homePhoneField));

                ContactNotificationUtil.showStandardNotification("Adding contact", mainAppInstance);

            } else if (clickEvent.getButton() == clearButton) {
                eachPresenter.clearAddContactForm();

                ContactNotificationUtil.showStandardNotification("Form has been reset", mainAppInstance);

            } else if (clickEvent.getButton() == cancelButton) {
                ContactNotificationUtil.showStandardNotification("Canceling Add Contact Action", mainAppInstance);

            } else {
                throw new UnsupportedOperationException("Functionality not yet implemented!");
            }
        }
    }

    private AbstractLayout getAddContactMainLayout() {
        this.setSizeFull();

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(Boolean.TRUE);
        mainLayout.setMargin(Boolean.TRUE);

        final HorizontalLayout addContactLayout = new HorizontalLayout();
        addContactLayout.setWidth(100, UNITS_PERCENTAGE);
        addContactLayout.setHeight(null);
        addContactLayout.setSpacing(Boolean.TRUE);


        firstNameField.setInputPrompt("Enter FirstName...");
        lastNameField.setInputPrompt("Enter LastName...");
        addressField.setInputPrompt("Enter Address...");
        emailField.setInputPrompt("Enter Email...");
        jobTitleField.setInputPrompt("Enter JobTitle...");
        jobDescField.setInputPrompt("Enter JobDescription...");
        ageField.setInputPrompt("Enter Age...");
        mobilePhoneField.setInputPrompt("Enter Mobile Phone...");
        homePhoneField.setInputPrompt("Enter Home Phone...");

        addContactLayout.addComponent(firstNameField);
        addContactLayout.addComponent(lastNameField);
        addContactLayout.addComponent(addressField);
        addContactLayout.addComponent(emailField);
        addContactLayout.addComponent(jobTitleField);
        addContactLayout.addComponent(jobDescField);
        addContactLayout.addComponent(ageField);
        addContactLayout.addComponent(mobilePhoneField);
        addContactLayout.addComponent(homePhoneField);

        HorizontalLayout actionsLayout = new HorizontalLayout();
        actionsLayout.setSpacing(Boolean.TRUE);
        actionsLayout.setHeight(100, UNITS_PIXELS);

        actionsLayout.addComponent(addButton);
        actionsLayout.addComponent(clearButton);
        actionsLayout.addComponent(cancelButton);

        mainLayout.addComponent(addContactLayout);
        mainLayout.addComponent(actionsLayout);
        mainLayout.setComponentAlignment(actionsLayout, Alignment.BOTTOM_LEFT);

        addButton.addListener(this);
        clearButton.addListener(this);
        cancelButton.addListener(this);

        return mainLayout;
    }
}
