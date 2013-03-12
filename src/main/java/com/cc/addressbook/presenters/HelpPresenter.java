package com.cc.addressbook.presenters;

import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.HelpView;

/**
 * @author cclaudiu
 * 
 */
public class HelpPresenter implements HelpView.HelpViewListener {

	private final AddressbookMainView mainView;
	private final HelpView helpView;

	public HelpPresenter(AddressbookMainView mainView, HelpView helpView) {

		this.mainView = mainView;
		this.helpView = helpView;

        helpView.addListener(this);
	}

	@Override
	public void submitComplain(HelpView.ComplainEvent complainEvent) {


	}

    @Override
    public void showHelpView() {
        mainView.setMainViewMainComponent(helpView);
    }
}
