package com.cc.addressbook.application;

import com.cc.addressbook.appcontroller.NavigationControllerImpl;
import com.cc.addressbook.views.*;
import com.cc.addressbook.views.AddressbookMainView.NavigationController;
import com.cc.addressbook.views.types.ViewType;
import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 * 
 */

public class AddressbookApplication extends Application {

	public static final String ADDRESSBOOK_WINDOW_NAME = "Address-book Application";
	private static final long serialVersionUID = 1L;
	
	final NavigationController navigationController = NavigationControllerImpl.createInstance();

	@Override
	public void init() {
		
		final Window mainWindow = new Window(ADDRESSBOOK_WINDOW_NAME);
		final AddressbookMainView mainView = new AddressbookMainViewImpl();
		
		registerAllViewsIntoApplication(mainView);
		
		setMainWindow(mainWindow);
		mainWindow.setContent(mainView);
	}
	
	private void registerAllViewsIntoApplication(AddressbookMainView mainView) {
		navigationController.registerView(ViewType.MAIN_VIEW, mainView);
		navigationController.registerView(ViewType.SHOW_CONTACT_VIEW, new ShowAllContactsViewImpl());
		navigationController.registerView(ViewType.ADD_CONTACT_VIEW, new AddContactViewImpl());
		navigationController.registerView(ViewType.EDIT_CONTACT_VIEW, new EditContactsViewImpl());
		navigationController.registerView(ViewType.SEARCH_CONTACT_VIEW, new SearchContactViewImpl());
		navigationController.registerView(ViewType.SHARE_CONTACT_VIEW, new ShareContactViewImpl());
	}
}