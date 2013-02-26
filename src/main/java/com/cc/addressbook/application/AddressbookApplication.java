package com.cc.addressbook.application;

import com.cc.addressbook.presenters.AddressbookMainViewPresenter;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.AddressbookMainViewImpl;
import com.cc.addressbook.views.ShowAllContactsView;
import com.cc.addressbook.views.ShowAllContactsViewImpl;
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
		final ShowAllContactsView showAllContactsView = new ShowAllContactsViewImpl();

		// Tie together the Main Window - with the Customer Table View, and Customer Model
		final AddressbookMainViewPresenter presenter = new AddressbookMainViewPresenter(mainView, showAllContactsView);

		setMainWindow(mainWindow);
		mainWindow.setContent(mainView);
	}
}