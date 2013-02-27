package com.cc.addressbook.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author cclaudiu
 *
 */

public enum HorizontalMenuBarConstants implements AddressboookHorizontalMenu, DefaultMenuBar {

    ADD_CONTACT("Add New Contact"),
    EDIT_CONTACT("Edit Contact"),
    SHARE_CONTACT("Share Contact"),
    HELP_BUTTON("Help Me"),
    DEFAULT("");
    
    private String tabSheetName;
    
    private HorizontalMenuBarConstants(String tabSheetName) {
    	this.tabSheetName = tabSheetName;
    }
    
    public String getButtonValue() {
    	return tabSheetName;
    }
    
    public static HorizontalMenuBarConstants getMenuTabSheetInstance(String tabCaption) {
    	List<HorizontalMenuBarConstants> enumInstances = Arrays.asList(values());
    	HorizontalMenuBarConstants wantedInstance = DEFAULT;
    	
    	for(HorizontalMenuBarConstants eachInstance : enumInstances) {
    		if(tabCaption.equals(eachInstance.tabSheetName)) {
    			wantedInstance = eachInstance;
    			break;
    		}
    	}
    	return wantedInstance;
    }
}