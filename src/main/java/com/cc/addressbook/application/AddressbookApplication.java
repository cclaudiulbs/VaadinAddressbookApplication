package com.cc.addressbook.application;

import com.cc.addressbook.presenters.AddressbookMainWindowPresenter;
import com.cc.addressbook.windows.AddressbookMainView;
import com.cc.addressbook.windows.AddressbookMainViewImpl;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 * 
 */

public class AddressbookApplication extends Application {

	// private static final String REINDEER_THEME = "reindeermods";

	public static final String ADDRESSBOOK_WINDOW_NAME = "Address-book Application";
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		// setTheme(REINDEER_THEME);
		
		final Window mainWindow = new Window(ADDRESSBOOK_WINDOW_NAME);

		final AddressbookMainView mainView = new AddressbookMainViewImpl();

		// Tie together the Main Window - with the Customer Table View, and Customer Model
		final AddressbookMainWindowPresenter presenter = new AddressbookMainWindowPresenter(mainView);

		setMainWindow(mainWindow);
		mainWindow.setContent(mainView.getMainView());
	}
}