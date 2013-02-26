package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.AddressbookVerticalMenu;
import com.cc.addressbook.constants.AddressboookHorizontalMenu;
import com.cc.addressbook.constants.DefaultMenuBar;
import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.cc.addressbook.views.AddContactViewImpl;
import com.cc.addressbook.views.HelpViewImpl;
import com.cc.addressbook.views.SearchContactViewImpl;
import com.cc.addressbook.views.ShareContactViewImpl;
import com.cc.addressbook.views.ShowAllContactsViewImpl;
import com.cc.addressbook.windows.AddressbookMainView;
import com.cc.addressbook.windows.AddressbookMainView.AddressbookMainViewListener;

public class AddressbookMainWindowPresenter implements AddressbookMainViewListener {

	private AddressbookMainView mainView;

	public AddressbookMainWindowPresenter(AddressbookMainView mainView) {
		this.mainView = mainView;
		
		mainView.addListener(this);
	}
	
	@Override
	public void selectedMenuEvent(DefaultMenuBar pressedMenu) {
		if(pressedMenu instanceof AddressboookHorizontalMenu) {
				if(pressedMenu == HorizontalMenuBarConstants.ADD_CONTACT) {
					mainView.setMainViewSecondComponent(new AddContactViewImpl());
					
				} else if(pressedMenu == HorizontalMenuBarConstants.EDIT_CONTACT) {
					// when Edit Menu is clicked, a form as in the add contact is showed, and when the user clicks on a row from the Table Contact Listed,
					// these fields form is populated with data contained from the current Selected Item;
					
				} else if(pressedMenu == HorizontalMenuBarConstants.SHARE_CONTACT) {
					mainView.setRootViewMainComponent(new ShareContactViewImpl());
					
				} else if(pressedMenu == HorizontalMenuBarConstants.HELP_BUTTON) {
					mainView.setRootViewMainComponent(new HelpViewImpl());
					
				}
	    } else if(pressedMenu instanceof AddressbookVerticalMenu) {
				if(pressedMenu == VerticalMenuBarConstants.SHOW_ALL_PROPERTY) {
						mainView.setMainViewFirstComponent(new ShowAllContactsViewImpl());
						mainView.getRootViewMainComponent().setVisible(Boolean.TRUE);
						
				} else if(pressedMenu == VerticalMenuBarConstants.SEARCH_CONTACT_PROPERTY) {
					mainView.setMainViewSecondComponent(new SearchContactViewImpl());
				}
		}
	}
}