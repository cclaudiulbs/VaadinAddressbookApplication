package com.cc.addressbook.application;

import com.cc.addressbook.appcontroller.NavigationControllerImpl;
import com.cc.addressbook.views.AddContactView;
import com.cc.addressbook.views.AddContactViewImpl;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.AddressbookMainView.NavigationController;
import com.cc.addressbook.views.AddressbookMainViewImpl;
import com.cc.addressbook.views.EditContactsView;
import com.cc.addressbook.views.EditContactsViewImpl;
import com.cc.addressbook.views.HelpView;
import com.cc.addressbook.views.HelpViewImpl;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.SearchContactViewImpl;
import com.cc.addressbook.views.ShareContactView;
import com.cc.addressbook.views.ShareContactViewImpl;
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
	
	final NavigationController navigationController = NavigationControllerImpl.createInstance();

	@Override
	public void init() {
		
		final Window mainWindow = new Window(ADDRESSBOOK_WINDOW_NAME);
		final AddressbookMainView mainView = new AddressbookMainViewImpl();
		final ShowAllContactsView showContactsView = new ShowAllContactsViewImpl();
		final AddContactView addContactView = new AddContactViewImpl();
		final EditContactsView editContactView = new EditContactsViewImpl();
		final SearchContactView searchContactView = new SearchContactViewImpl();
		final ShareContactView shareContactView = new ShareContactViewImpl();
		final HelpView helpView = new HelpViewImpl();
		
		setMainWindow(mainWindow);
		mainWindow.setContent(mainView);
	}
	
	private void registerAllViewsIntoApplication() {
		navigationController.registerView(new ShowAllContactsViewImpl());
	}
}