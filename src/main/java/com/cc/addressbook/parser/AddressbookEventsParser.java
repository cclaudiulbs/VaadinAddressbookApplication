package com.cc.addressbook.parser;

import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.menu.types.MenuActionType;
import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

/**
 * @author cclaudiu
 * 
 * Based on Overloading each method is called, which returns an instance of the pressed UI Component {@link MenuActionType}
 */

public final class AddressbookEventsParser {
	private AddressbookEventsParser() { }
	
	public static VerticalMenuBarActions getEventType(Property.ValueChangeEvent valueChangeEvent) {
		String selectedItem = String.valueOf(valueChangeEvent.getProperty().getValue());
		
		return VerticalMenuBarActions.getVerticalMenuBarInstance(selectedItem);
	}
	
	public static HorizontalMenuBarActions getEventType(TabSheet.SelectedTabChangeEvent selectedTabEvent) {
		TabSheet selectedTabSheet = selectedTabEvent.getTabSheet();
		Tab selectedTab = selectedTabSheet.getTab(selectedTabSheet.getSelectedTab());
		String tabCaption = selectedTab.getCaption();
		
		return HorizontalMenuBarActions.getMenuTabSheetInstance(tabCaption);
	}
}