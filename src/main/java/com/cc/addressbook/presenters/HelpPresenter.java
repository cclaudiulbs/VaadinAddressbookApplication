package com.cc.addressbook.presenters;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.HelpView;

/**
 * @author cclaudiu
 * 
 */
public class HelpPresenter implements HelpView.HelpViewListener {

	private final AddressbookMainView mainAppView;
	private final HelpView helpView;

	public HelpPresenter(AddressbookMainView mainAppView, HelpView helpView) {

		this.mainAppView = mainAppView;
		this.helpView = helpView;

        helpView.addListener(this);
	}

	@Override
	public void submitComplain(HelpView.ComplainEvent complainEvent) {

	}

    @Override
    public void showHelpView() {
        mainAppView.clearSelectedComponents();
        mainAppView.setMainViewFirstComponent(helpView);
    }
}
