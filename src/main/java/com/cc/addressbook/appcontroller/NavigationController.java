package com.cc.addressbook.appcontroller;

import com.cc.addressbook.menu.types.MenuActionType;
import com.cc.addressbook.views.DefaultView;
import com.cc.addressbook.views.types.ViewType;

/**
 * @author cclaudiu
 *         <br/></br>
 *         Main Application Controller can easily be interchanged with another Implementation
 *         to design a new flow of app navigation
 */
public interface NavigationController {
    void dispatch(MenuActionType pressedAction);

    void registerView(ViewType viewType, DefaultView view);
}
