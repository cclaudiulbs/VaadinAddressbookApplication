package com.cc.addressbook.views;

import com.cc.addressbook.appcontroller.NavigationController;
import com.cc.addressbook.appcontroller.NavigationControllerImpl;
import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.presenters.AddressbookEventsParser;
import com.vaadin.data.Property;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author cclaudiu
 *         <p/>
 *         IMPORTANT:
 *         Events are per view; if the view is read-only there's NO View Presenter to Impl
 *         If the View is dispatched, and the View Consists of a Logic, each click within the View
 *         will BE handled by a Presenter!!!
 */

public class AddressbookMainViewImpl
        extends CustomComponent
        implements AddressbookMainView,
        TabSheet.SelectedTabChangeListener,
        Property.ValueChangeListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(AddressbookMainViewImpl.class.getName());

    private final VerticalLayout mainLayout = new VerticalLayout();
    private final HorizontalSplitPanel mainViewSplitPanel = new HorizontalSplitPanel();
    private final VerticalSplitPanel insideMainViewSplitPanel = new VerticalSplitPanel();
    private final NavigationController mainAppController = NavigationControllerImpl.createInstance();

    public AddressbookMainViewImpl() {
        buildMainWindowLayout();
    }

    @Override
    public void setMainViewMainComponent(DefaultView component) {
        mainViewSplitPanel.setSecondComponent(component);
    }

    @Override
    public Component getMainViewMainComponent() {
        return mainViewSplitPanel.getSecondComponent();
    }

    @Override
    public void setMainViewFirstComponent(DefaultView component) {
        insideMainViewSplitPanel.setFirstComponent(component);
    }

    @Override
    public void setMainViewSecondComponent(DefaultView component) {
        insideMainViewSplitPanel.setSecondComponent(component);
    }

    /**
     * TabSheet.SelectedTabChangeListener - For the TabSheet Like Menu
     */
    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        mainAppController.dispatch(AddressbookEventsParser.getEventType(event));
    }

    /**
     * Property.ValueChangeListener - For the Tree Like Menu
     */
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        mainAppController.dispatch(AddressbookEventsParser.getEventType(event));
    }


    // ----------------- Build the Main Layout of Main App View ---------------------//
    private void buildMainWindowLayout() {
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

        menuTabSheet.getTab(addContact).setCaption(HorizontalMenuBarActions.ADD_CONTACT.getButtonValue());
        menuTabSheet.getTab(searchContact).setCaption(HorizontalMenuBarActions.EDIT_CONTACT.getButtonValue());
        menuTabSheet.getTab(shareContact).setCaption(HorizontalMenuBarActions.SHARE_CONTACT.getButtonValue());
        menuTabSheet.getTab(help).setCaption(HorizontalMenuBarActions.HELP_BUTTON.getButtonValue());

        menuTabSheet.setImmediate(Boolean.TRUE);
        menuTabSheet.setSelectedTab(-1);

        // ----------------- Format the LeftHand Menu with Logo ----------------------------//
        VerticalLayout leftSideMainViewSplit = new VerticalLayout();
        leftSideMainViewSplit.setSizeFull();
        Tree mainTreeOptions = new Tree();
        Embedded addressbookLogo = getAddressbookLogo();

        mainTreeOptions.addItem(VerticalMenuBarActions.SHOW_ALL_PROPERTY.getMenuBarPropertyVal());
        mainTreeOptions.addItem(VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY.getMenuBarPropertyVal());

        mainTreeOptions.setCaption("Other Options");
        mainTreeOptions.setImmediate(Boolean.TRUE);
        mainTreeOptions.setNullSelectionAllowed(Boolean.FALSE);

        leftSideMainViewSplit.addComponent(mainTreeOptions);
        leftSideMainViewSplit.addComponent(addressbookLogo);
        leftSideMainViewSplit.setComponentAlignment(addressbookLogo, Alignment.MIDDLE_CENTER);

        // ----------------- AddAll Main View Components to the MainViewSplitPanel ----------------//
        mainViewSplitPanel.setFirstComponent(leftSideMainViewSplit);
        mainViewSplitPanel.setSecondComponent(insideMainViewSplitPanel);
        mainViewSplitPanel.getSecondComponent().setVisible(Boolean.FALSE);
        mainViewSplitPanel.setSplitPosition(240, Sizeable.UNITS_PIXELS, Boolean.FALSE);
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

    private Embedded getAddressbookLogo() {
        URL imageUrl = null;

        try {
            imageUrl = new URL("http://www.digitaltrends.com/wp-content/uploads/2012/02/address-book.png");
        } catch (MalformedURLException e) {
            LOG.severe("Exception occured while trying to access the addressbook image URL; Original Exception msg=" + e.getMessage());
        }

        Resource imageResource = new ExternalResource(imageUrl);
        Embedded image = new Embedded("", imageResource);
        image.setWidth(300, UNITS_PIXELS);
        image.setHeight(300, UNITS_PIXELS);

        return image;
    }
}