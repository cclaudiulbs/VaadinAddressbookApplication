package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

/**
 * @author cclaudiu
 * 
 * Based on Overloading the correct method is called, which returns an instance of the pressed UI Component
 */

public final class AddressbookEventsParser {
	private AddressbookEventsParser() { }
	
	public static VerticalMenuBarConstants getEventType(Property.ValueChangeEvent valueChangeEvent) {
		String selectedItem = String.valueOf(valueChangeEvent.getProperty().getValue());
		
		return VerticalMenuBarConstants.getVerticalMenuBarInstance(selectedItem);
	}
	
	public static HorizontalMenuBarConstants getEventType(TabSheet.SelectedTabChangeEvent selectedTabEvent) {
		TabSheet selectedTabSheet = selectedTabEvent.getTabSheet();
		Tab selectedTab = selectedTabSheet.getTab(selectedTabSheet.getSelectedTab());
		String tabCaption = selectedTab.getCaption();
		
		return HorizontalMenuBarConstants.getMenuTabSheetInstance(tabCaption);
	}
}