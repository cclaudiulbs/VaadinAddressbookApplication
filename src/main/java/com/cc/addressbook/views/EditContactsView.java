package com.cc.addressbook.views;

import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */
public interface EditContactsView {

	void addListener();
	
	public interface EditContactListener {
		void updateContactInformation(Component component);
	}
}
