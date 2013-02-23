package com.cc.addressbook.constants;

/**
 * @author cclaudiu
 *
 */

public enum MenuBarButtons {

    ADD_CONTACT("Add New Contact"),
    EDIT_CONTACT("Edit Contact"),
    SHARE_CONTACT("Share Contact"),
    HELP_BUTTON("Help Me"),
    SHOW_CONTACTS("Show All Contacts"),
    SEARCH_CONTACT("Search Contact");
    
    private String buttonValue;
    private MenuBarButtons(String buttonValue) {
    	this.buttonValue = buttonValue;
    }
    
    public String getButtonValue() {
    	return buttonValue;
    }
}