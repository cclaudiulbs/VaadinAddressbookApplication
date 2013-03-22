package com.cc.addressbook.views;

import com.cc.addressbook.pojo.UserSubject;

/**
 * @author cclaudiu
 */

public interface LoginPopupView extends DefaultView {

    void showView();

    void addPresenter(AuthenticatePresenter presenter);

    void closeView();

    interface AuthenticatePresenter {
        UserSubject login(UserSubject user);

        void cancelLogin();
    }
}