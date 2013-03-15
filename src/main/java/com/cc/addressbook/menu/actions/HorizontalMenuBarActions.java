package com.cc.addressbook.menu.actions;

import java.util.Arrays;
import java.util.List;

import com.cc.addressbook.menu.types.AddressboookHorizontalMenu;
import com.cc.addressbook.menu.types.MenuActionType;

/**
 * @author cclaudiu
 *
 */

public enum HorizontalMenuBarActions implements AddressboookHorizontalMenu, MenuActionType {

    ADD_CONTACT("Add New Contact"),
    DELETE_CONTACT("Delete Contact"),
    EDIT_CONTACT("Edit Contact"),
    SHARE_CONTACT("Share Contact"),
    HELP_BUTTON("Help Me"),
    LOGIN_USER("Login"),
    DEFAULT("");
    
    private String tabSheetName;
    
    private HorizontalMenuBarActions(String tabSheetName) {
    	this.tabSheetName = tabSheetName;
    }
    
    public String getButtonValue() {
    	return tabSheetName;
    }
    
    public static HorizontalMenuBarActions getMenuTabSheetInstance(String tabCaption) {
    	List<HorizontalMenuBarActions> enumInstances = Arrays.asList(values());
    	HorizontalMenuBarActions wantedInstance = DEFAULT;
    	
    	for(HorizontalMenuBarActions eachInstance : enumInstances) {
    		if(tabCaption.equals(eachInstance.tabSheetName)) {
    			wantedInstance = eachInstance;
    			break;
    		}
    	}
    	return wantedInstance;
    }
}