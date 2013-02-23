package com.cc.addressbook.windows;

import com.cc.addressbook.constants.MenuBarButtons;
import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainWindow {

	// ------ Enable Listeners  ------//
    public interface AddressbookMainWindowListener {
        void selectedMenuEvent(MenuBarButtons constants);
    }

    void addListener(AddressbookMainWindowListener listener);
    
	// --------- Enable Views ----------//
    void setMainWindowComponent(Component component);
    void setMainViewFirstComponent(Component component);
    void setMainViewSecondComponent(Component component);
}