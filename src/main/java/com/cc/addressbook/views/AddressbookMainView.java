package com.cc.addressbook.views;

import com.cc.addressbook.menu.types.MenuActionType;
import com.cc.addressbook.views.types.ViewType;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;


/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainView extends DefaultView, ComponentContainer {

	/**
	 * Main Application Controller can easily be interchanged with another Implementation
	 * to design a new flow of app navigation
	 */
    public interface NavigationController {
        void dispatch(MenuActionType pressedAction);
        
		void registerView(ViewType viewType, DefaultView view);
    }

	// ------------------------- Enable Views -------------------------//
    void setMainViewMainComponent(DefaultView component);
    void setMainViewFirstComponent(DefaultView component);
    void setMainViewSecondComponent(DefaultView component);
    Component getMainViewMainComponent();
}