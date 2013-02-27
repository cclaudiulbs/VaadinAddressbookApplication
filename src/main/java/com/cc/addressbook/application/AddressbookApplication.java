package com.cc.addressbook.application;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.AddressbookMainViewImpl;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 * 
 */

public class AddressbookApplication extends Application {

	public static final String ADDRESSBOOK_WINDOW_NAME = "Address-book Application";
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		
		final Window mainWindow = new Window(ADDRESSBOOK_WINDOW_NAME);
		final AddressbookMainView mainView = new AddressbookMainViewImpl();

		setMainWindow(mainWindow);
		mainWindow.setContent(mainView);
	}
}