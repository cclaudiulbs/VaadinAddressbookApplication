package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.AddressbookVerticalMenu;
import com.cc.addressbook.constants.AddressboookHorizontalMenu;
import com.cc.addressbook.constants.DefaultMenuBar;
import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.cc.addressbook.models.ContactsModel;
import com.cc.addressbook.views.AddContactViewImpl;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.AddressbookMainView.AddressbookMainViewListener;
import com.cc.addressbook.views.HelpViewImpl;
import com.cc.addressbook.views.SearchContactViewImpl;
import com.cc.addressbook.views.ShareContactViewImpl;
import com.cc.addressbook.views.ShowAllContactsView;

public class AddressbookMainViewPresenter implements AddressbookMainViewListener {

	private AddressbookMainView mainAppView;
	private ShowAllContactsView showContactsView;
	private ContactsModel contactsModel;

	public AddressbookMainViewPresenter(AddressbookMainView mainView, ShowAllContactsView showContactsView) {
		this.mainAppView = mainView;
		this.showContactsView = showContactsView;
		
		contactsModel = new ContactsModel();
		
		mainView.addListener(this);
	}
	
	// TODO: do refactor this and split into a Strategy; delegate the pressedMenu to a "AddressbookMenuHandler"
	// inside this Handler, try to drop the if/else logic with replacing it to a Polymorphic way of selecting at
	// runtime the correct concrete implementer (nice to have, but we have some actions to delegate and views to set based on these UI interactions)
	// Step 1: SHOW_ALL -> make it work! with PagedTable
	@Override
	public void selectedMenuEvent(DefaultMenuBar pressedMenu) {
		
		// the HorizontalMenu was pressed
		if(pressedMenu instanceof AddressboookHorizontalMenu) {
			
				if(pressedMenu == HorizontalMenuBarConstants.ADD_CONTACT) {
					mainAppView.setMainViewSecondComponent(new AddContactViewImpl());
					
				} else if(pressedMenu == HorizontalMenuBarConstants.EDIT_CONTACT) {
					// when Edit Menu is clicked, a form as in the add contact is showed, and when the user clicks on a row from the Table Contact Listed,
					// these fields form is populated with data contained from the current Selected Item;
					
				} else if(pressedMenu == HorizontalMenuBarConstants.SHARE_CONTACT) {
					mainAppView.setMainViewMainComponent(new ShareContactViewImpl());
					
				} else if(pressedMenu == HorizontalMenuBarConstants.HELP_BUTTON) {
					mainAppView.setMainViewMainComponent(new HelpViewImpl());
					
				}
				
		// the Left Vertical Menu was pressed
	    } else if(pressedMenu instanceof AddressbookVerticalMenu) {
				if(pressedMenu == VerticalMenuBarConstants.SHOW_ALL_PROPERTY) {
						mainAppView.setMainViewFirstComponent(showContactsView);
						
						// until the user accessed the application, the mainView is NOT seen as a split part, only when the user clicks SHOW
						mainAppView.getMainViewMainComponent().setVisible(Boolean.TRUE);
						
						showContactsView.getContactsContainer().addAll(contactsModel.getCustomers());
						
				} else if(pressedMenu == VerticalMenuBarConstants.SEARCH_CONTACT_PROPERTY) {
					mainAppView.setMainViewSecondComponent(new SearchContactViewImpl());
				}
		}
	}
}