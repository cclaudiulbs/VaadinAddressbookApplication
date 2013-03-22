package com.cc.addressbook.presenters;

import com.cc.addressbook.models.UserSecurityServiceModel;
import com.cc.addressbook.pojo.UserSubject;
import com.cc.addressbook.views.LoginPopupView;

/**
 * @author cclaudiu
 * 
 */

public class AuthenticatePresenterImpl implements LoginPopupView.AuthenticatePresenter {

	private final UserSecurityServiceModel loginService = new UserSecurityServiceModel();
	private final LoginPopupView loggingView;

	public AuthenticatePresenterImpl(LoginPopupView loginView) {
		this.loggingView = loginView;

		loginView.addPresenter(this);
	}

	@Override
	public UserSubject login(UserSubject user) {
		return loginService.attemptToLogin(user);
	}

	@Override
	public void cancelLogin() {
		loggingView.closeView();
	}
}
