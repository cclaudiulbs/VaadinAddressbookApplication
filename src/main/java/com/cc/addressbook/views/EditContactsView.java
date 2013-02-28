package com.cc.addressbook.views;

import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */
public interface EditContactsView extends DefaultView {

	void addListener();
	
	public interface EditContactListener {
		void updateContactInformation(Component component);
	}
}
