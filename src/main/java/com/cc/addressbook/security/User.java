package com.cc.addressbook.security;

/**
 * @author cclaudiu
 *
 * Plain Old Resource that wrapps the user credentials and maybe some more user related information
 */
public class User {
    private final String login;
    private final String password;
    private final Boolean rememberMe;

    public User(String login, String password, Boolean rememberMe) {
        this.login = login;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
}
