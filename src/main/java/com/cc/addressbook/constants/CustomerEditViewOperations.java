package com.cc.addressbook.constants;

/**
 * @author cclaudiu
 */

public enum CustomerEditViewOperations {
    SAVE("Save"),
    CLEAR("Reset"),
    CANCEL("Cancel");

    private final String value;

    private CustomerEditViewOperations(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
