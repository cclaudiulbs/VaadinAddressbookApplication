package com.cc.addressbook.windows;

import com.cc.addressbook.constants.DefaultMenuBar;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;


/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainView {

	// ------ Enable Listeners  ------//
    public interface AddressbookMainViewListener {
        void selectedMenuEvent(DefaultMenuBar pressedButton);
    }

    void addListener(AddressbookMainViewListener listener);
    
	// --------- Enable Views ----------//
    void setRootViewMainComponent(Component component);
    void setMainViewFirstComponent(Component component);
    void setMainViewSecondComponent(Component component);
    CustomComponent getMainView();
    Component getRootViewMainComponent();
}