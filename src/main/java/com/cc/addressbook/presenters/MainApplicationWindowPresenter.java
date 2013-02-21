package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.MenuBarConstants;
import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.CustomerModel;
import com.cc.addressbook.views.CustomerTableView;
import com.cc.addressbook.windows.MainApplicationWindow;
import com.vaadin.data.util.BeanItemContainer;

/**
 * @author cclaudiu
 *
 */

public class MainApplicationWindowPresenter
        implements MainApplicationWindow.MainApplicationWindowListener {

    private MainApplicationWindow mainWindow;
    private CustomerTableView customerTableView;
    private CustomerModel customerModel;

    public MainApplicationWindowPresenter(MainApplicationWindow mainWindow,
                                          CustomerTableView customerTableView,
                                          CustomerModel model) {
        this.mainWindow = mainWindow;
        this.customerTableView = customerTableView;
        this.customerModel = model;

        this.mainWindow.addListener(this);
    }

    @Override
    public void selectMenuEvent(MenuBarConstants menuAction) {
        switch (menuAction) {
            case ADD_CONTACT:
            break;

            case SEARCH_CONTACT:
            break;

            case SHARE_CONTACT:
            break;

            case HELP_BUTTON:
            break;

            case SHOW_ALL_TREE:
                // Step 1: Populate the BeanItemContainer by calling the presenter of the CustomerTable
                BeanItemContainer<PersonEntity> customerContainer = customerTableView.getPersonContainer();
                // TODO - delegate to CustomerTablePresenter more elegant
                CustomerTablePresenter customerPresenter = new CustomerTablePresenter(customerModel, customerTableView);
                customerPresenter.populateContainer(customerContainer);

                // Step 2: set the first component to be the customer Table View where data can be seen
                mainWindow.setMainComponent(customerTableView);
            break;

            case SEARCH_TREE:
            break;
        }
    }
}