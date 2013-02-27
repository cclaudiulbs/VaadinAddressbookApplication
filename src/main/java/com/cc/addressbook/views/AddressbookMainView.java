package com.cc.addressbook.views;

import com.cc.addressbook.menu.types.MenuActionType;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;


/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainView extends ComponentContainer {

	/**
	 * Main Application Controller can easily be interchanged with another Implementation
	 * to design a new flow of app navigation
	 */
    public interface NavigationController {
        void dispatch(MenuActionType pressedAction);
    }

	// ------------------------- Enable Views -------------------------//
    void setMainViewMainComponent(Component component);
    void setMainViewFirstComponent(Component component);
    void setMainViewSecondComponent(Component component);
    Component getMainViewMainComponent();
}