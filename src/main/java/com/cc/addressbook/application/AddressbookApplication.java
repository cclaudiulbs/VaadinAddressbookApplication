package com.cc.addressbook.application;

import com.cc.addressbook.windows.AddressbookMainWindowImpl;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 */

public class AddressbookApplication extends Application {

	private static final long serialVersionUID = 1L;
	
	public static final String ADDRESSBOOK_WINDOW_NAME = "Address-book Application";

    @Override public void init() {
        final Window mainWindow = new AddressbookMainWindowImpl(ADDRESSBOOK_WINDOW_NAME);
        
        // Tie together the Main Window - with the Customer Table View, and Customer Model

        setMainWindow(mainWindow);
    }
}