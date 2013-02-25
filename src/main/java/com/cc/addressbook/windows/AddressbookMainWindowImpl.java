package com.cc.addressbook.windows;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.cc.addressbook.presenters.AddressbookEventsParser;
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
     * TODO: implement the Runtime Strategy Pattern for the correct instance to be created
     * which instance will return, after invoking the getView() the correct impl view back;
     */
    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        for(AddressbookMainWindowListener presenterListener : mainWindowlisteners) {
        	HorizontalMenuBarConstants pressedTab = AddressbookEventsParser.getEventBaseClass(event);
        	
        	presenterListener.selectedMenuEvent(pressedTab);
        }
    }

    /**
     * Property.ValueChangeListener - For the Tree Like Menu
     */
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        for(AddressbookMainWindowListener presenterListener : mainWindowlisteners) {
        	VerticalMenuBarConstants pressedTree = AddressbookEventsParser.getEventBaseClass(event);
        	
        	// delegate to presenter the UI Binding and Further Logic
        	presenterListener.selectedMenuEvent(pressedTree);
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

        menuTabSheet.getTab(addContact).setCaption(HorizontalMenuBarConstants.ADD_CONTACT.getButtonValue());
        menuTabSheet.getTab(searchContact).setCaption(HorizontalMenuBarConstants.EDIT_CONTACT.getButtonValue());
        menuTabSheet.getTab(shareContact).setCaption(HorizontalMenuBarConstants.SHARE_CONTACT.getButtonValue());
        menuTabSheet.getTab(help).setCaption(HorizontalMenuBarConstants.HELP_BUTTON.getButtonValue());
        
        menuTabSheet.setImmediate(Boolean.TRUE);

        Tree mainTreeOptions = new Tree();
        
        mainTreeOptions.addItem(VerticalMenuBarConstants.SHOW_ALL_PROPERTY.getMenuBarPropertyVal());
        mainTreeOptions.addItem(VerticalMenuBarConstants.SEARCH_CONTACT_PROPERTY.getMenuBarPropertyVal());
        
        mainTreeOptions.setCaption("Other Options");
        mainTreeOptions.setImmediate(Boolean.TRUE);
        mainTreeOptions.setNullSelectionAllowed(Boolean.FALSE);

        mainWindowSplitPanel.setFirstComponent(mainTreeOptions);
        mainWindowSplitPanel.setSecondComponent(mainViewSplitPanel);
        mainWindowSplitPanel.getSecondComponent().setVisible(Boolean.FALSE);
        mainWindowSplitPanel.setSplitPosition(17, Sizeable.UNITS_PERCENTAGE, Boolean.FALSE);
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