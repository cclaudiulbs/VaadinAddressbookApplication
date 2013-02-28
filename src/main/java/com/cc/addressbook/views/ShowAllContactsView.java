package com.cc.addressbook.views;

import java.util.List;

import com.cc.addressbook.entities.TableEntity;

/**
 * @author cclaudiu
 * 
 */

public interface ShowAllContactsView <T extends TableEntity> extends DefaultView {

    void addListener(ShowAllContactsListener listener);
	void addContacts(List<T> customers);

    public interface ShowAllContactsListener {
        void showContacts();
    }

	List<T> getContactsList();
}