package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.DefaultMenuBar;
import com.cc.addressbook.windows.AddressbookMainWindow;
import com.cc.addressbook.windows.AddressbookMainWindow.AddressbookMainWindowListener;

public class AddressbookMainWindowListenerImpl implements AddressbookMainWindowListener {

	private AddressbookMainWindow mainWindow;

	public AddressbookMainWindowListenerImpl(AddressbookMainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		mainWindow.addListener(this);
	}
	
	@Override
	public void selectedMenuEvent(DefaultMenuBar pressedMenu) {
		
	}

}
