package com.cc.addressbook.models;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.cc.addressbook.pojo.UserSubject;
import org.apache.shiro.subject.Subject;

/**
 * @author cclaudiu
 * 
 *         Via Apache Shiro -- validate the User and Store this user under Session
 */

public class UserSecurityServiceModel {

	public UserSubject attemptToLogin(UserSubject userSubject) {
		UsernamePasswordToken unamePasswordToken = new UsernamePasswordToken(userSubject.getEmail(),
				userSubject.getPassword(), userSubject.isRememberMe());

        unamePasswordToken.setRememberMe(userSubject.isRememberMe());

        Subject currentUser = SecurityUtils.getSubject();

        // TODO: embrace the logic of callback for handling the Exceptions in other class
//        currentUser.login(unamePasswordToken);

		return userSubject;
	}
}
