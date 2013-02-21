package com.cc.addressbook.application;

import com.cc.addressbook.models.CustomerModel;
import com.cc.addressbook.presenters.MainApplicationWindowPresenter;
import com.cc.addressbook.views.CustomerTableView;
import com.cc.addressbook.views.CustomerTableViewImpl;
import com.cc.addressbook.windows.MainApplicationWindow;
import com.cc.addressbook.windows.MainApplicationWindowImpl;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 */

public class AddressBookApplication extends Application {

    public static final String ADDRESSBOOK_WINDOW_NAME = "Address-book Application";

    @Override public void init() {
        final Window mainWindow = new MainApplicationWindowImpl(ADDRESSBOOK_WINDOW_NAME);
        final CustomerTableView customerTableView = new CustomerTableViewImpl();
        final CustomerModel customerModel = new CustomerModel();

        // Tie together the Main Window - with the Customer Table View, and Customer Model
        new MainApplicationWindowPresenter((MainApplicationWindow) mainWindow, customerTableView, customerModel);

        setMainWindow(mainWindow);
    }
}