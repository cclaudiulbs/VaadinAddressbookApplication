package com.cc.addressbook.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author cclaudiu
 *
 */

public enum HorizontalMenuBarConstants implements DefaultMenuBar {

    ADD_CONTACT("Add New Contact", 1),
    EDIT_CONTACT("Edit Contact", 2),
    SHARE_CONTACT("Share Contact", 3),
    HELP_BUTTON("Help Me", 4),
    DEFAULT("", -1);
    
    private String tabSheetName;
    private Integer tabIdx;
    
    private HorizontalMenuBarConstants(String tabSheetName, Integer idxPosition) {
    	this.tabSheetName = tabSheetName;
    	this.tabIdx = idxPosition;
    }
    
    public String getButtonValue() {
    	return tabSheetName;
    }
    
    public HorizontalMenuBarConstants getMenuTabSheetInstance(Integer idx) {
    	List<HorizontalMenuBarConstants> enumInstances = Arrays.asList(values());
    	HorizontalMenuBarConstants wantedInstance = DEFAULT;
    	
    	for(HorizontalMenuBarConstants eachInstance : enumInstances) {
    		if(idx == eachInstance.tabIdx) {
    			wantedInstance = eachInstance;
    		}
    	}
    	return wantedInstance;
    }
}