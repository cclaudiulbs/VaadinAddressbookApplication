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
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
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
    private final HorizontalSplitPanel mainWindowSplitPanel = new HorizontalSplitPanel();
    private final VerticalSplitPanel mainViewSplitPanel = new VerticalSplitPanel();

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
        mainWindowSplitPanel.setSecondComponent(component);
    }
    
    @Override 
    public void setMainViewFirstComponent(Component component) {
    	mainViewSplitPanel.setFirstComponent(component);
    }
    
    @Override
    public void setMainViewSecondComponent(Component component) {
    	mainViewSplitPanel.setSecondComponent(component);
    }
    
    /**
     * TabSheet.SelectedTabChangeListener - For the TabSheet Like Menu
     */
    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        for(AddressbookMainWindowListener presenterListener : mainWindowlisteners) {
            MenuBarButtons menuAction = MenuBarButtons.valueOf(String.valueOf(((Label) event.getTabSheet().getSelectedTab()).getValue()));
            presenterListener.selectedMenuEvent(menuAction);
        }
    }

    /**
     * Property.ValueChangeListener - For the Tree Like Menu
     */
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

        // -------------- Define Header Layout of Main WIndow ---------------//
        VerticalLayout headerLayout = new VerticalLayout();
        Label welcomeHeader = new Label("<b>Welcome To AddressBook Application</b>", Label.CONTENT_XHTML);
        welcomeHeader.setSizeUndefined();
        headerLayout.addComponent(welcomeHeader);
        headerLayout.setComponentAlignment(welcomeHeader, Alignment.BOTTOM_CENTER);

        // -------------- Define Menu Buttons ----------------//
        TabSheet menuTabSheet = new TabSheet();

        Label addContact = new Label();
        Label searchContact = new Label();
        Label shareContact = new Label();
        Label help = new Label();

        menuTabSheet.addTab(addContact);
        menuTabSheet.addTab(searchContact);
        menuTabSheet.addTab(shareContact);
        menuTabSheet.addTab(help);

        menuTabSheet.getTab(addContact).setCaption(MenuBarButtons.ADD_CONTACT.name());
        menuTabSheet.getTab(searchContact).setCaption(MenuBarButtons.SEARCH_CONTACT.name());
        menuTabSheet.getTab(shareContact).setCaption(MenuBarButtons.SHARE_CONTACT.name());
        menuTabSheet.getTab(help).setCaption(MenuBarButtons.HELP_BUTTON.name());

        menuTabSheet.setImmediate(Boolean.TRUE);

        Tree mainTreeOptions = new Tree("Other Options");
        mainTreeOptions.addItem(MenuBarButtons.SHOW_CONTACTS.getButtonValue());
        mainTreeOptions.addItem(MenuBarButtons.SEARCH_CONTACT.getButtonValue());
        mainTreeOptions.setImmediate(Boolean.TRUE);

        mainWindowSplitPanel.setFirstComponent(mainTreeOptions);
        mainWindowSplitPanel.setSecondComponent(null);
        mainWindowSplitPanel.setSplitPosition(20.0f, Sizeable.UNITS_PERCENTAGE, Boolean.FALSE);
        mainWindowSplitPanel.setSizeFull();
        

        // -------------- Define Footer of the Main Window --------------//
        Panel footerLayout = new Panel();
        footerLayout.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        // Panel Footer needs a Size for the Width, if undefined it will shrink to its nested component
        
        // ------- Since width is undefined for HorizontalLayout -------//
        HorizontalLayout insideFooter = new HorizontalLayout();
        insideFooter.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        
        Label footerText = new Label("<b>Copyright 2013 by cclaudiu</b>", Label.CONTENT_XHTML);
        footerText.setSizeUndefined();
        
        insideFooter.addComponent(footerText);
        insideFooter.setComponentAlignment(footerText, Alignment.TOP_CENTER);
        
        footerLayout.setContent(insideFooter);
        // rather than adding a new component to Panel
        

        // -------------- Adding all Components to Main Layout of the Main Window ---------//
        mainLayout.addComponent(headerLayout);
        mainLayout.addComponent(menuTabSheet);
        mainLayout.addComponent(mainWindowSplitPanel);
        mainLayout.addComponent(footerLayout);
        mainLayout.setExpandRatio(mainWindowSplitPanel, 1.0f);

        // ------- Registering Listeners of the Main Window - Menu Buttons -------//
        menuTabSheet.addListener((TabSheet.SelectedTabChangeListener) this);
        mainTreeOptions.addListener((Property.ValueChangeListener) this);

        // replace the current content of the main Window
        setContent(mainLayout);
    }
}