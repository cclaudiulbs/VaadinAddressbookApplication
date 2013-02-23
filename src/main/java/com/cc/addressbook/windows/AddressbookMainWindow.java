package com.cc.addressbook.windows;

import com.cc.addressbook.constants.MenuBarButtons;
import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainWindow {

    public interface AddressbookMainWindowListener {
        void selectedMenuEvent(MenuBarButtons constants);
    }

    void addListener(AddressbookMainWindowListener listener);

    void setMainWindowComponent(Component component);
}