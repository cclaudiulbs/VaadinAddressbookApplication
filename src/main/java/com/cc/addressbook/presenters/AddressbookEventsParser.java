package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;

/**
 * @author cclaudiu
 * 
 * Based on Overloading the correct method is called, which returns an instance of the pressed UI Component
 */

public final class AddressbookEventsParser {
	
	public static VerticalMenuBarConstants getEventBaseClass(Property.ValueChangeEvent valueChangeEvent) {
		String selectedItem = String.valueOf(valueChangeEvent.getProperty().getValue());
		
		return VerticalMenuBarConstants.DEFAULT.getVerticalMenuBarInstance(selectedItem);
	}
	public static HorizontalMenuBarConstants getEventBaseClass(TabSheet.SelectedTabChangeEvent selectedTab) {
		
		int tabIdx = selectedTab.getTabSheet().getTabIndex();
		
		return HorizontalMenuBarConstants.DEFAULT.getMenuTabSheetInstance(tabIdx);
	}
	
}