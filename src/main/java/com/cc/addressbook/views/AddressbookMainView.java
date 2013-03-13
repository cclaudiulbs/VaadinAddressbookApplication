package com.cc.addressbook.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * @author cclaudiu
 * 
 */

public interface AddressbookMainView extends DefaultView, ComponentContainer {

	// ------------------------- Enable Views -------------------------//
	/**
	 * Working with Views, the main Window is used only once inside the application This View represents the
	 * Right-Hand-Side of the split main Window
	 * 
	 * @param component
	 */
	void setMainViewMainComponent(DefaultView component);

	/**
	 * Working with Views, the main Window is used only once inside the application This View represents the Top-Right
	 * side split of the main Window
	 * 
	 * @param component
	 */
	void setMainViewFirstComponent(DefaultView component);

	/**
	 * Working with Views, the main Window is used only once inside the application This View represents the
	 * Bottom-Right side split of the main Window
	 * 
	 * @param component
	 */
	void setMainViewSecondComponent(DefaultView component);

	/**
	 * When a Menu Is Selected/Clicked the focus() is de-selected so that it can be clickable again while navigating
	 * through other Menu Options In short:Sets the default selectedTab to an unknown position, so that a menu button
	 * can fire an event when clicked
	 */
	void clearSelectedComponents();

	Component getMainViewMainComponent();
}
