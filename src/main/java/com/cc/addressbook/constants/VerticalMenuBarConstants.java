package com.cc.addressbook.constants;

/**
 * @author cclaudiu
 *
 */

public enum VerticalMenuBarConstants implements DefaultMenuBar {

	SHOW_ALL_PROPERTY("Show All Contacts"),
	SEARCH_CONTACT_PROPERTY("Search Contact"),
	DEFAULT("");
	
	private String propertyValue;
	
	private VerticalMenuBarConstants(String newValue) {
		this.propertyValue = newValue;
	}
	
	public String getMenuBarPropertyVal() {
		return propertyValue;
	}
	
	public VerticalMenuBarConstants getVerticalMenuBarInstance(String uiValue) {
		VerticalMenuBarConstants wantedMenuInstance = DEFAULT;
		
		for(VerticalMenuBarConstants eachProperty : values()) {
			if(uiValue.equals(eachProperty.propertyValue)) {
				wantedMenuInstance = eachProperty;
				break;
			}
		}
		return wantedMenuInstance;
	}
}