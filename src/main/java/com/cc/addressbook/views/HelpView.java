package com.cc.addressbook.views;

/**
 * @author cclaudiu
 * 
 */
public interface HelpView extends DefaultView {

	void addListener(HelpViewListener complainListener);

	interface HelpViewListener {

		void submitComplain(ComplainEvent complainEvent);

		void showHelpView();
	}

	class ComplainEvent {

		private final String complainMessage;

		public ComplainEvent(String complainMessage) {
			this.complainMessage = complainMessage;
		}

		public String getComplainMessage() {
			return complainMessage;
		}
	}
}
