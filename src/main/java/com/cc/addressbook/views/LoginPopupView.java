package com.cc.addressbook.views;

import com.cc.addressbook.pojo.UserSubject;

/**
 * @author cclaudiu
 */

public interface LoginPopupView extends DefaultView {

    void showView();

    void addPresenter(LoginPresenter presenter);

    void closeView();

    interface LoginPresenter {
        UserSubject login(UserSubject user);
        void cancelLogin() ;
    }
}