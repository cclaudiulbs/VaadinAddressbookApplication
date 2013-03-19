package com.cc.addressbook.views.types;

import com.cc.addressbook.appcontroller.NavigationControllerImpl;
import com.cc.addressbook.application.AddressbookApplication;

/**
 * @author cclaudiu 
 * <br><br>
 * Acts as a mapping for all registered views, used inside {@link NavigationControllerImpl}
 * forall registered views, declared in {@link AddressbookApplication}
 */
public enum ViewType {
	MAIN_VIEW,
	ADD_CONTACT_VIEW,
	EDIT_CONTACT_VIEW,
	SEARCH_CONTACT_VIEW,
	SHOW_CONTACT_VIEW,
	SHARE_CONTACT_VIEW,
    LOGIN_CONTACT_VIEW,
	HELP_VIEW;
}
