package com.cc.addressbook.presenters.strategy;

import com.cc.addressbook.constants.DefaultMenuBar;
import com.cc.addressbook.views.ShowAllContactsViewImpl;
import com.cc.addressbook.windows.AddressbookMainWindow;

public class ShowContactsStrategy implements UIComponentStrategy {
	
	private AddressbookMainWindow mainWindow;

	public ShowContactsStrategy(AddressbookMainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void dispatch(DefaultMenuBar pressedMenu) {
		this.mainWindow.setMainViewFirstComponent(new ShowAllContactsViewImpl());
	}

}
