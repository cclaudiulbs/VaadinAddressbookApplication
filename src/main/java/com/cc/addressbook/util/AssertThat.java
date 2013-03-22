package com.cc.addressbook.util;

/**
 * @author cclaudiu
 *
 */
public final class AssertThat {
    private AssertThat() { }

    public static final void notNull(Object subject) {
        if(subject == null) {
            throw new IllegalArgumentException("Subject cannot be null!");
        }
    }

    public static final void notEmptyPassword(char[] password) {
        if(password != null && password.length == 0) {
            throw new IllegalArgumentException("Password cannot be null!");
        }
    }
}
