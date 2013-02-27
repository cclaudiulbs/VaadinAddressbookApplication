package com.cc.addressbook.menu.actions;

import com.cc.addressbook.menu.types.AddressbookVerticalMenu;
import com.cc.addressbook.menu.types.MenuActionType;

/**
 * @author cclaudiu
 *
 */

public enum VerticalMenuBarActions implements MenuActionType, AddressbookVerticalMenu {

	SHOW_ALL_PROPERTY("Show All Contacts"),
	SEARCH_CONTACT_PROPERTY("Search Contact"),
	DEFAULT("");
	
	private String propertyValue;
	
	private VerticalMenuBarActions(String newValue) {
		this.propertyValue = newValue;
	}
	
	public String getMenuBarPropertyVal() {
		return propertyValue;
	}
	
	public static VerticalMenuBarActions getVerticalMenuBarInstance(String uiValue) {
		VerticalMenuBarActions wantedMenuInstance = DEFAULT;
		
		for(VerticalMenuBarActions eachProperty : values()) {
			if(uiValue.equals(eachProperty.propertyValue)) {
				wantedMenuInstance = eachProperty;
				break;
			}
		}
		return wantedMenuInstance;
	}
}