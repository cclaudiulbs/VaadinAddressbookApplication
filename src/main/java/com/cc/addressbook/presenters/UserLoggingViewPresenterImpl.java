package com.cc.addressbook.presenters;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.LoginPopupView;

/**
 * @author cclaudiu
 *
 */

public class UserLoggingViewPresenterImpl implements AddressbookMainView.UserLoggingViewPresenter {

    private final AddressbookMainView mainView;
    private final LoginPopupView loginPopupView;

    public UserLoggingViewPresenterImpl(AddressbookMainView mainView, LoginPopupView loginPopupView) {
        this.mainView = mainView;
        this.loginPopupView = loginPopupView;

        mainView.addPresenter(this);
    }

    @Override
    public void delegateToLoginView() {
        loginPopupView.showView();
    }
}