package com.cc.addressbook.views;

import com.cc.addressbook.application.AddressbookApplication;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class HelpViewImpl extends CustomComponent implements HelpView, Button.ClickListener {

	private static final long serialVersionUID = 1L;

    private Button addComplainButton = new Button("Submit Complain");
    private Button cancelComplainButton = new Button("Cancel Complain");

    private final List<HelpViewListener> presenters = new ArrayList<>();
    private final AddressbookApplication mainAppInstance;

    public HelpViewImpl(AddressbookApplication mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
        setCompositionRoot(buildHelpViewDefaultLayout());
	}

    @Override
    public void addListener(HelpViewListener complainListener) {
        presenters.add(complainListener);
    }

    private AbstractLayout buildHelpViewDefaultLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(Boolean.TRUE);
        mainLayout.setSpacing(Boolean.TRUE);

        AbstractField complainComponent = new RichTextArea("Complain Board");
        complainComponent.setWidth(400, UNITS_PIXELS);
        complainComponent.setHeight(400, UNITS_PIXELS);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponent(addComplainButton);
        buttonsLayout.addComponent(cancelComplainButton);

        addComplainButton.addListener(this);
        cancelComplainButton.addListener(this);

        mainLayout.addComponent(complainComponent);
        mainLayout.addComponent(buttonsLayout);
        mainLayout.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_LEFT);
        mainLayout.setComponentAlignment(complainComponent, Alignment.MIDDLE_CENTER);

        return mainLayout;
    }

    private String populateHelpContentText() {
        final StringBuilder helpTextContent = new StringBuilder()
                .append("Yellow!<br>")
                .append("This is a nice application build in Vaadin with some interesting and top rated Java Frameworks, like: <br>")
                .append("Vaadin Core<br>")
                .append("JPA using EclipseLink as Implementation<br>")
                .append("PagedTable<br>")
                .append("Apache Shiro Security<br>")
                .append("Build on top of Model View Presenter Controller Architecture(which provides the best way of implementing)")
                .append("an Enterprise loosely coupled Java application")
                .append("in a Event Driven Mechanism)");

        return helpTextContent.toString();
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        for(HelpViewListener eachPresenter: presenters) {
            if(event.getButton() == addComplainButton) {
                ContactNotificationUtil.showStandardNotification("Submitting Contact Complain", mainAppInstance);
            } else if(event.getButton() == cancelComplainButton) {

            }
        }
    }
}
