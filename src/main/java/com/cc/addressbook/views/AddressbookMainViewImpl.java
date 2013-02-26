package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.constants.HorizontalMenuBarConstants;
import com.cc.addressbook.constants.VerticalMenuBarConstants;
import com.cc.addressbook.presenters.AddressbookEventsParser;
import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * @author cclaudiu
 *
 */

public class AddressbookMainViewImpl 
					extends CustomComponent 
					implements AddressbookMainView,
                   			   TabSheet.SelectedTabChangeListener,
                   			   Property.ValueChangeListener 
{

	private static final long serialVersionUID = 1L;
	
	private final VerticalLayout mainLayout = new VerticalLayout();
	private final List<AddressbookMainViewListener> mainViewlisteners = new ArrayList<>();
    private final HorizontalSplitPanel mainViewSplitPanel = new HorizontalSplitPanel();
    private final VerticalSplitPanel insideMainViewSplitPanel = new VerticalSplitPanel();

    public AddressbookMainViewImpl() {
    	buildMainWindowLayout();
    }

    /***
     * Presenter register itself to this event
     */
    @Override
    public void addListener(AddressbookMainViewListener listener) {
        mainViewlisteners.add(listener);
    }

    @Override
    public void setMainViewMainComponent(Component component) {
        mainViewSplitPanel.setSecondComponent(component);
    }
    
	@Override
	public Component getMainViewMainComponent() {
		return mainViewSplitPanel.getSecondComponent();
	}
    
    @Override 
    public void setMainViewFirstComponent(Component component) {
    	insideMainViewSplitPanel.setFirstComponent(component);
    }
    
    @Override
    public void setMainViewSecondComponent(Component component) {
    	insideMainViewSplitPanel.setSecondComponent(component);
    }
    
    /**
     * TabSheet.SelectedTabChangeListener - For the TabSheet Like Menu
     */
    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        for(AddressbookMainViewListener presenterListener : mainViewlisteners) {
        	HorizontalMenuBarConstants pressedTab = AddressbookEventsParser.getEventType(event);
        	
        	// delegate to presenter to handle UI Binding and further logic
        	presenterListener.selectedMenuEvent(pressedTab);
        }
    }

    /**
     * Property.ValueChangeListener - For the Tree Like Menu
     */
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        for(AddressbookMainViewListener presenterListener : mainViewlisteners) {
        	VerticalMenuBarConstants pressedTreeOperation = AddressbookEventsParser.getEventType(event);
        	
        	// delegate to presenter the UI Binding and Further Logic
        	presenterListener.selectedMenuEvent(pressedTreeOperation);
        }
    }

    private  void buildMainWindowLayout() {
    	setSizeFull();
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

        mainViewSplitPanel.setFirstComponent(mainTreeOptions);
        mainViewSplitPanel.setSecondComponent(insideMainViewSplitPanel);
        mainViewSplitPanel.getSecondComponent().setVisible(Boolean.FALSE);
        mainViewSplitPanel.setSplitPosition(17, Sizeable.UNITS_PERCENTAGE, Boolean.FALSE);
        mainViewSplitPanel.setSizeFull();
        

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
        mainLayout.addComponent(mainViewSplitPanel);
        mainLayout.addComponent(footerLayout);
        mainLayout.setExpandRatio(mainViewSplitPanel, 1.0f);

        // ------- Registering Listeners of the Main Window - Menu Buttons -------//
        menuTabSheet.addListener((TabSheet.SelectedTabChangeListener) this);
        mainTreeOptions.addListener((Property.ValueChangeListener) this);
        
        setCompositionRoot(mainLayout);
    }


}