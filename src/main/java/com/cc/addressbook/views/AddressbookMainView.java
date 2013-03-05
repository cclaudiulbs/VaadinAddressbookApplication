package com.cc.addressbook.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;


/**
 * @author cclaudiu
 *
 */

public interface AddressbookMainView extends DefaultView, ComponentContainer {

	// ------------------------- Enable Views -------------------------//
    void setMainViewMainComponent(DefaultView component);
    void setMainViewFirstComponent(DefaultView component);
    void setMainViewSecondComponent(DefaultView component);
    Component getMainViewMainComponent();
}