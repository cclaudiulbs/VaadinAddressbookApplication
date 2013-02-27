package com.cc.addressbook.presenters;

import com.cc.addressbook.models.ContactsModel;
import com.cc.addressbook.views.ShowAllContactsView.ShowAllContactsListener;

public class ShowAllContactsPresenter implements ShowAllContactsListener {

	private ContactsModel model = new ContactsModel();
	
	@Override
	public void showContacts() {
		model.getCustomers();
	}

}
