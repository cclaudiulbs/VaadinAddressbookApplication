package com.cc.addressbook.views;

import com.cc.addressbook.constants.DefaultMenuBar;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;


/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainView extends ComponentContainer {

	// ------ Enable Listeners  ------//
    public interface AddressbookMainViewListener {
        void selectedMenuEvent(DefaultMenuBar pressedButton);
    }

    void addListener(AddressbookMainViewListener listener);
    
	// --------- Enable Views ----------//
    void setMainViewMainComponent(Component component);
    void setMainViewFirstComponent(Component component);
    void setMainViewSecondComponent(Component component);
    Component getMainViewMainComponent();
}