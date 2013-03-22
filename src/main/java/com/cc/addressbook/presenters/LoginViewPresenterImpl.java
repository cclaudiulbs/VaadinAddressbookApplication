package com.cc.addressbook.presenters;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.LoginPopupView;

/**
 * @author cclaudiu
 *
 */

public class LoginViewPresenterImpl implements AddressbookMainView.LoginViewPresenter {

    private final LoginPopupView loginPopupView;

    public LoginViewPresenterImpl(AddressbookMainView mainView, LoginPopupView loginPopupView) {
        this.loginPopupView = loginPopupView;

        mainView.addPresenter(this);
    }

    @Override
    public void delegateToLoginView() {
        loginPopupView.showView();
    }
}