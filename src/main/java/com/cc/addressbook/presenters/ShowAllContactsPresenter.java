package com.cc.addressbook.presenters;

import com.cc.addressbook.models.ContactsCrudServiceModel;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.ShowAllContactsView;
import com.cc.addressbook.views.ShowAllContactsView.ShowAllContactsListener;

public class ShowAllContactsPresenter implements ShowAllContactsListener {

	private final AddressbookMainView mainView;
	private final ShowAllContactsView showContactsView;
	private final ContactsCrudServiceModel model;

	public ShowAllContactsPresenter(AddressbookMainView view, ShowAllContactsView showContactsView) {
		this.mainView = view;
		this.showContactsView = showContactsView;
		this.model = new ContactsCrudServiceModel();

        showContactsView.addPresenter(this);
	}

	@Override
	public void showContacts() {
		mainView.setMainViewFirstComponent(showContactsView);
		
		// until the user accessed the application, the mainView is NOT seen as a split part, only when the user clicks SHOW
		mainView.getMainViewMainComponent().setVisible(Boolean.TRUE);
		
		showContactsView.addContacts(model.getCustomers());
	}
}