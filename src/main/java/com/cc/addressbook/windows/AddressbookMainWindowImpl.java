package com.cc.addressbook.windows;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.constants.MenuBarButtons;
import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 */

public class AddressbookMainWindowImpl extends Window
        implements AddressbookMainWindow,
                   TabSheet.SelectedTabChangeListener,
                   Property.ValueChangeListener {

	private static final long serialVersionUID = 1L;
	
	private final List<AddressbookMainWindowListener> mainWindowlisteners = new ArrayList<>();
    private final HorizontalSplitPanel mainSplitPanel = new HorizontalSplitPanel();

    public AddressbookMainWindowImpl(String windowName) {
    	super(windowName);
        buildMainWindowLayout();
    }

    /***
     * Presenter register itself to this event
     */
    @Override
    public void addListener(AddressbookMainWindowListener listener) {
        mainWindowlisteners.add(listener);
    }

    @Override
    public void setMainWindowComponent(Component component) {
        mainSplitPanel.setSecondComponent(component);
    }

    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        for(AddressbookMainWindowListener presenterListener : mainWindowlisteners) {
            MenuBarButtons menuAction = MenuBarButtons.valueOf(String.valueOf(((Label) event.getTabSheet().getSelectedTab()).getValue()));
            presenterListener.selectedMenuEvent(menuAction);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        for(AddressbookMainWindowListener presenterListener : mainWindowlisteners) {
            // TODO - Caused by: java.lang.NullPointerException: Name is null
            MenuBarButtons menuAction = MenuBarButtons.valueOf(String.valueOf(event.getProperty()));
            presenterListener.selectedMenuEvent(menuAction);
        }
    }

    private void buildMainWindowLayout() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        VerticalLayout headerLayout = new VerticalLayout();
        Label welcomeHeader = new Label("<b>Welcome To AddressBook Application</b>", Label.CONTENT_XHTML);
        welcomeHeader.setSizeUndefined();
        headerLayout.addComponent(welcomeHeader);
        headerLayout.setComponentAlignment(welcomeHeader, Alignment.BOTTOM_CENTER);

        TabSheet menuTabSheet = new TabSheet();

        Label addContact = new Label(MenuBarButtons.ADD_CONTACT.name());
        Label searchContact = new Label(MenuBarButtons.SEARCH_CONTACT.name());
        Label shareContact = new Label(MenuBarButtons.SHARE_CONTACT.name());
        Label help = new Label(MenuBarButtons.HELP_BUTTON.name());

        menuTabSheet.addTab(addContact);
        menuTabSheet.addTab(searchContact);
        menuTabSheet.addTab(shareContact);
        menuTabSheet.addTab(help);

        menuTabSheet.getTab(addContact).setCaption(MenuBarButtons.ADD_CONTACT.name());
        menuTabSheet.getTab(searchContact).setCaption(MenuBarButtons.SEARCH_CONTACT.name());
        menuTabSheet.getTab(shareContact).setCaption(MenuBarButtons.SHARE_CONTACT.name());
        menuTabSheet.getTab(help).setCaption(MenuBarButtons.HELP_BUTTON.name());

        menuTabSheet.getTab(addContact).setDescription("Add new Contact");
        menuTabSheet.getTab(addContact).setDescription("Search for Contact");
        menuTabSheet.getTab(addContact).setDescription("Share a Contact");
        menuTabSheet.getTab(addContact).setDescription("Help me");
        menuTabSheet.setImmediate(Boolean.TRUE);

        Tree mainTreeOptions = new Tree("Other Options");
        mainTreeOptions.addItem(MenuBarButtons.SHOW_ALL_TREE.name());
        mainTreeOptions.addItem(MenuBarButtons.SEARCH_TREE.name());
        mainTreeOptions.setImmediate(Boolean.TRUE);

        mainSplitPanel.setFirstComponent(mainTreeOptions);
        mainSplitPanel.setSecondComponent(null);
        mainSplitPanel.setSplitPosition(15.0f, Sizeable.UNITS_PERCENTAGE, Boolean.FALSE);
        mainSplitPanel.setSizeFull();

        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        Label footerText = new Label("<b>Copyright 2013 by cclaudiu</b>", Label.CONTENT_XHTML);
        footerText.setSizeUndefined();
        footerLayout.addComponent(footerText);
        footerLayout.setComponentAlignment(footerText, Alignment.TOP_CENTER);

        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(menuTabSheet);
        mainLayout.addComponent(mainSplitPanel);
        mainLayout.addComponent(footerLayout);
        mainLayout.setExpandRatio(mainSplitPanel, 1.0f);

        menuTabSheet.addListener((TabSheet.SelectedTabChangeListener) this);
        mainTreeOptions.addListener((Property.ValueChangeListener) this);

        setContent(mainLayout);
    }

}