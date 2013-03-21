package com.cc.addressbook.views;

import com.cc.addressbook.appcontroller.NavigationController;
import com.cc.addressbook.appcontroller.NavigationControllerImpl;
import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.parser.AddressbookEventsParser;
import com.vaadin.data.Property;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        Property.ValueChangeListener,
        Button.ClickListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(AddressbookMainViewImpl.class.getName());

    private final VerticalLayout mainLayout = new VerticalLayout();
    private final HorizontalSplitPanel mainViewSplitPanel = new HorizontalSplitPanel();
    private final VerticalSplitPanel insideMainViewSplitPanel = new VerticalSplitPanel();
    private final NavigationController mainAppController = NavigationControllerImpl.createInstance();
    private final List<UserLoggingViewPresenter> loginViewPresenters = new ArrayList<>();

    private final Tree mainTreeOptions = new Tree();
    private final TabSheet menuTabSheet = new TabSheet();


    public AddressbookMainViewImpl() {
        buildMainWindowLayout();
    }

    /* Application Logic Related */
    @Override
    public void setMainViewMainComponent(DefaultView component) {
        mainViewSplitPanel.setSecondComponent(component);
    }

    @Override
    public Component getMainViewMainComponent() {
        return mainViewSplitPanel.getSecondComponent();
    }

    @Override
    public void addPresenter(UserLoggingViewPresenter loginViewPresenter) {
        loginViewPresenters.add(loginViewPresenter);
    }

    @Override
    public void setMainViewFirstComponent(DefaultView component) {
        insideMainViewSplitPanel.setFirstComponent(component);
    }

    @Override
    public void setMainViewSecondComponent(DefaultView component) {
        insideMainViewSplitPanel.setSecondComponent(component);
    }

    @Override
    public void clearSelectedComponents() {
        menuTabSheet.setSelectedTab(menuTabSheet.getTab(0));

        for (Object eachVisibleMenuTree : mainTreeOptions.getVisibleItemIds()) {
            mainTreeOptions.unselect(eachVisibleMenuTree);
        }
    }

    /* Vaadin Listeners related */

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

    /**
     * Button.ClickEvent - Logging in user
     *
     * @param event
     */
    @Override
    public void buttonClick(Button.ClickEvent event) {
        mainAppController.dispatch(AddressbookEventsParser.getEventType(event));
    }


    // ----------------- Build the Main Layout of Main App View ---------------------//
    private void buildMainWindowLayout() {
        setSizeFull();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(Boolean.TRUE);

        // ---- Define the Behavior Event when the Login button is clicked -----//
        final Button loginButton = new Button(HorizontalMenuBarActions.LOGIN_USER.getButtonValue());
        loginButton.addListener((Button.ClickListener) this);

        // -------------- Define Header Layout of Main WIndow ---------------//
        VerticalLayout headerLayout = new VerticalLayout();
        Label welcomeHeaderText = new Label("<b>Welcome To AddressBook Application</b>", Label.CONTENT_XHTML);
        welcomeHeaderText.setSizeUndefined();
        headerLayout.addComponent(welcomeHeaderText);
        headerLayout.addComponent(loginButton);
        headerLayout.setComponentAlignment(welcomeHeaderText, Alignment.MIDDLE_CENTER);
        headerLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);

        // -------------- Define Menu Buttons ----------------//
        Label leftHandDesignPurposeLabel = new Label();
        Label addContact = new Label();
        Label deleteContact = new Label();
        Label searchContact = new Label();
        Label shareContact = new Label();
        Label help = new Label();

        menuTabSheet.addTab(leftHandDesignPurposeLabel);
        menuTabSheet.addTab(addContact);
        menuTabSheet.addTab(deleteContact);
        menuTabSheet.addTab(searchContact);
        menuTabSheet.addTab(shareContact);
        menuTabSheet.addTab(help);

        menuTabSheet.getTab(addContact).setCaption(HorizontalMenuBarActions.ADD_CONTACT.getButtonValue());
        menuTabSheet.getTab(deleteContact).setCaption(HorizontalMenuBarActions.DELETE_CONTACT.getButtonValue());
        menuTabSheet.getTab(searchContact).setCaption(HorizontalMenuBarActions.EDIT_CONTACT.getButtonValue());
        menuTabSheet.getTab(shareContact).setCaption(HorizontalMenuBarActions.SHARE_CONTACT.getButtonValue());
        menuTabSheet.getTab(help).setCaption(HorizontalMenuBarActions.HELP_BUTTON.getButtonValue());

        menuTabSheet.setImmediate(Boolean.TRUE);

        // ----------------- Format the LeftHand Menu with Logo ----------------------------//
        VerticalLayout leftSideMainViewSplit = new VerticalLayout();
        leftSideMainViewSplit.setSizeFull();

        Embedded addressbookLogo = getAddressbookLogo();

        mainTreeOptions.addItem(VerticalMenuBarActions.SHOW_ALL_PROPERTY.getMenuBarPropertyVal());
        mainTreeOptions.addItem(VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY.getMenuBarPropertyVal());

        mainTreeOptions.setCaption("Other Options");
        mainTreeOptions.setImmediate(Boolean.TRUE);
        mainTreeOptions.setNullSelectionAllowed(Boolean.FALSE);

        // In case there's an error in the system, this message is nicely displayed on the Menu Icon
        mainTreeOptions.setErrorHandler(new ComponentErrorHandler() {
            @Override
            public boolean handleComponentError(ComponentErrorEvent event) {
                mainTreeOptions.setComponentError(new UserError(event.getThrowable().getLocalizedMessage()));
                return Boolean.TRUE;
            }
        });

        leftSideMainViewSplit.addComponent(mainTreeOptions);
        leftSideMainViewSplit.addComponent(addressbookLogo);
        leftSideMainViewSplit.setComponentAlignment(addressbookLogo, Alignment.TOP_CENTER);

        // Fix the split position of the insideMainView Component
        insideMainViewSplitPanel.setSplitPosition(25, Sizeable.UNITS_PERCENTAGE, Boolean.TRUE);

        // ----------------- AddAll Main View Components to the MainViewSplitPanel ----------------//
        mainViewSplitPanel.setFirstComponent(leftSideMainViewSplit);
        mainViewSplitPanel.setSecondComponent(insideMainViewSplitPanel);
        mainViewSplitPanel.getSecondComponent().setVisible(Boolean.TRUE);
        mainViewSplitPanel.setSplitPosition(addressbookLogo.getWidth(), Sizeable.UNITS_PIXELS, Boolean.FALSE);
        mainViewSplitPanel.setSizeFull();
        mainViewSplitPanel.setLocked(Boolean.TRUE);


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
        menuTabSheet.addListener(this);
        mainTreeOptions.addListener(this);

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