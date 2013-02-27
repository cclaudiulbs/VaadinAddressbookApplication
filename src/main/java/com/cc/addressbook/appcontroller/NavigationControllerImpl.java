package com.cc.addressbook.appcontroller;

import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.menu.types.AddressbookVerticalMenu;
import com.cc.addressbook.menu.types.AddressboookHorizontalMenu;
import com.cc.addressbook.menu.types.MenuActionType;
import com.cc.addressbook.presenters.AddContactPresenter;
import com.cc.addressbook.presenters.EditContactPresenter;
import com.cc.addressbook.presenters.HelpPresenter;
import com.cc.addressbook.presenters.SearchContactPresenter;
import com.cc.addressbook.presenters.ShareContactPresenter;
import com.cc.addressbook.presenters.ShowAllContactsPresenter;
import com.cc.addressbook.views.AddressbookMainView.NavigationController;

/**
 * @author cclaudiu
 *
 * NavigationController is the First Entry Point into the Addressbook application
 * It controls the UI View Flow/Navigation by delegating to the corresponding View Presenter
 * This being said, is aware, has compositions of every View Presenters
 * 
 * This is the main responsibility of NavigationController Class, nothing more; it is NOT aware of
 * the Model, of each presenter;
 *  
 * Each ViewImpl has a reference to this singleton NavigationController
 */

public final class NavigationControllerImpl implements NavigationController {
	
	private static NavigationController INSTANCE;
	
	private NavigationControllerImpl() { }
	
	public static NavigationController getInstance() {
		synchronized(NavigationControllerImpl.class) {
			if(INSTANCE == null) {
				INSTANCE = new NavigationControllerImpl();
			}
			return INSTANCE;
		}
	}
	
	@Override public void dispatch(MenuActionType pressedMenu) {
		
		// the HorizontalMenu was pressed
		if(pressedMenu instanceof AddressboookHorizontalMenu) {
			
				if(pressedMenu == HorizontalMenuBarActions.ADD_CONTACT) {
					final AddContactPresenter presenter = new AddContactPresenter();
					 presenter.addContactEvent();
					
				} else if(pressedMenu == HorizontalMenuBarActions.EDIT_CONTACT) {
					final EditContactPresenter presenter = new EditContactPresenter();
					 presenter.editContactEvent();
					
				} else if(pressedMenu == HorizontalMenuBarActions.SHARE_CONTACT) {
					final ShareContactPresenter presenter = new ShareContactPresenter();
					 presenter.shareContactEvent();
					
				} else if(pressedMenu == HorizontalMenuBarActions.HELP_BUTTON) {
					 final HelpPresenter presenter = new HelpPresenter();
					 presenter.helpPresenter();
					
				}
				
		// the Left Vertical Menu was pressed
	    } else if(pressedMenu instanceof AddressbookVerticalMenu) {
				if(pressedMenu == VerticalMenuBarActions.SHOW_ALL_PROPERTY) {
					final ShowAllContactsPresenter presenter = new ShowAllContactsPresenter();
					presenter.showContacts();
//						mainAppView.setMainViewFirstComponent(showContactsView);
						
						// until the user accessed the application, the mainView is NOT seen as a split part, only when the user clicks SHOW
//						mainAppView.getMainViewMainComponent().setVisible(Boolean.TRUE);
						
//						showContactsView.getContactsContainer().addAll(contactsModel.getCustomers());
						
				} else if(pressedMenu == VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY) {
					final SearchContactPresenter presenter = new SearchContactPresenter();
					presenter.searchContact();
				}
		}
	}
}