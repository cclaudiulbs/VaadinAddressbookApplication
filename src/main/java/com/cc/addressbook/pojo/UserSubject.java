package com.cc.addressbook.pojo;

import java.util.Arrays;

/**
 * @author cclaudiu
 *
 * Thread-Safe Pojo
 */

public final class UserSubject {

    private final String email;
    private final char[] password;
    private final boolean rememberMe;

    public UserSubject(String email, char[] password, boolean rememberMe) {
        this.email = email;
        this.password = Arrays.copyOf(password, password.length);
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return Arrays.copyOf(password, password.length);
    }

    public boolean isRememberMe() {
        return rememberMe;
    }
}