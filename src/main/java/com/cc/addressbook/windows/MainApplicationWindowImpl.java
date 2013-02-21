package com.cc.addressbook.windows;

import com.cc.addressbook.constants.MenuBarConstants;
import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *
 */

public class MainApplicationWindowImpl
        extends Window
        implements MainApplicationWindow,
                        TabSheet.SelectedTabChangeListener,
                                Property.ValueChangeListener {

    private final List<MainApplicationWindowListener> listeners = new ArrayList<>();
    private final HorizontalSplitPanel mainSplitPanel = new HorizontalSplitPanel();

    public MainApplicationWindowImpl(String caption) {
        super(caption);
        buildMainWindowLayout();
    }

    /***
     * Presenter register itself to this event
     */
    @Override
    public void addListener(MainApplicationWindowListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setMainComponent(Component component) {
        mainSplitPanel.setSecondComponent(component);
    }

    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        for(MainApplicationWindowListener presenterListener : listeners) {
            MenuBarConstants menuAction = MenuBarConstants.valueOf(String.valueOf(((Label) event.getTabSheet().getSelectedTab()).getValue()));
            presenterListener.selectMenuEvent(menuAction);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        for(MainApplicationWindowListener presenterListener : listeners) {
            // TODO - Caused by: java.lang.NullPointerException: Name is null
            MenuBarConstants menuAction = MenuBarConstants.valueOf(String.valueOf(event.getProperty()));
            presenterListener.selectMenuEvent(menuAction);
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

        Label addContact = new Label(MenuBarConstants.ADD_CONTACT.name());
        Label searchContact = new Label(MenuBarConstants.SEARCH_CONTACT.name());
        Label shareContact = new Label(MenuBarConstants.SHARE_CONTACT.name());
        Label help = new Label(MenuBarConstants.HELP_BUTTON.name());

        menuTabSheet.addTab(addContact);
        menuTabSheet.addTab(searchContact);
        menuTabSheet.addTab(shareContact);
        menuTabSheet.addTab(help);

        menuTabSheet.getTab(addContact).setCaption(MenuBarConstants.ADD_CONTACT.name());
        menuTabSheet.getTab(searchContact).setCaption(MenuBarConstants.SEARCH_CONTACT.name());
        menuTabSheet.getTab(shareContact).setCaption(MenuBarConstants.SHARE_CONTACT.name());
        menuTabSheet.getTab(help).setCaption(MenuBarConstants.HELP_BUTTON.name());

        menuTabSheet.getTab(addContact).setDescription("Add new Contact");
        menuTabSheet.getTab(addContact).setDescription("Search for Contact");
        menuTabSheet.getTab(addContact).setDescription("Share a Contact");
        menuTabSheet.getTab(addContact).setDescription("Help me");
        menuTabSheet.setImmediate(Boolean.TRUE);

        Tree mainTreeOptions = new Tree("Other Options");
        mainTreeOptions.addItem(MenuBarConstants.SHOW_ALL_TREE.name());
        mainTreeOptions.addItem(MenuBarConstants.SEARCH_TREE.name());
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