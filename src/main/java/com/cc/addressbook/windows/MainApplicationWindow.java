package com.cc.addressbook.windows;

import com.cc.addressbook.constants.MenuBarConstants;
import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */

public interface MainApplicationWindow {

    public interface MainApplicationWindowListener {
        void selectMenuEvent(MenuBarConstants constants);
    }

    void addListener(MainApplicationWindowListener listener);

    void setMainComponent(Component component);
}