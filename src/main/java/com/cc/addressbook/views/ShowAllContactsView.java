package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;

import java.util.List;

/**
 * @author cclaudiu
 * 
 */

public interface ShowAllContactsView extends DefaultView {

	void addPresenter(ShowAllContactsListener listener);

	void addContacts(List<PersonEntity> customers);

	public interface ShowAllContactsListener {

		void showContacts();
	}

	List<PersonEntity> getContactsList();
}