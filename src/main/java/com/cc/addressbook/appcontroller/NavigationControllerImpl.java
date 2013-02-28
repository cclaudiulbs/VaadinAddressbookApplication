package com.cc.addressbook.appcontroller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.AddressbookMainView.NavigationController;
import com.vaadin.ui.CustomComponent;

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
	private CustomComponent registeredViewReference;
	
	private NavigationControllerImpl() { }
	
	public static NavigationController createInstance() {
		synchronized(NavigationControllerImpl.class) {
			if(INSTANCE == null) {
				INSTANCE = new NavigationControllerImpl();
			}
		}
		return INSTANCE;
	}
	
	private final Map<String, CustomComponent> registeredViews = new ConcurrentHashMap<>();
	@Override public void registerView(CustomComponent view) {
		registeredViewReference = view;
		registeredViews.put(view.getCaption(), view);
	}
	
	@Override public void dispatch(MenuActionType pressedMenuAction) {
		
		// the HorizontalMenu was pressed
		if(pressedMenuAction instanceof AddressboookHorizontalMenu) {
			
				if(pressedMenuAction == HorizontalMenuBarActions.ADD_CONTACT) {
					final AddContactPresenter presenter = new AddContactPresenter();
					 presenter.addContactEvent();
					
				} else if(pressedMenuAction == HorizontalMenuBarActions.EDIT_CONTACT) {
					final EditContactPresenter presenter = new EditContactPresenter();
					 presenter.editContactEvent();
					
				} else if(pressedMenuAction == HorizontalMenuBarActions.SHARE_CONTACT) {
					final ShareContactPresenter presenter = new ShareContactPresenter();
					 presenter.shareContactEvent();
					
				} else if(pressedMenuAction == HorizontalMenuBarActions.HELP_BUTTON) {
					 final HelpPresenter presenter = new HelpPresenter();
					 presenter.helpPresenter();
					
				}
				
		// the Left Vertical Menu was pressed
	    } else if(pressedMenuAction instanceof AddressbookVerticalMenu) {
				if(pressedMenuAction == VerticalMenuBarActions.SHOW_ALL_PROPERTY) {
					final ShowAllContactsPresenter presenter = new ShowAllContactsPresenter( registeredViews.get(registeredViewReference.toString()) );
					presenter.showContacts();
						
				} else if(pressedMenuAction == VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY) {
					final SearchContactPresenter presenter = new SearchContactPresenter();
					presenter.searchContact();
				}
		}
	}
}