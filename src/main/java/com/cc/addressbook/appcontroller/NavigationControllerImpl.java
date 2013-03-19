package com.cc.addressbook.appcontroller;

import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.menu.types.AddressbookVerticalMenu;
import com.cc.addressbook.menu.types.AddressboookHorizontalMenu;
import com.cc.addressbook.menu.types.MenuActionType;
import com.cc.addressbook.presenters.*;
import com.cc.addressbook.views.*;
import com.cc.addressbook.views.types.ViewType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cclaudiu <br>
 *         <br>
 *         NavigationController is the First Entry Point into the Addressbook application It controls the UI View
 *         Flow/Navigation by delegating to the corresponding View Presenter This being said, is aware, has compositions
 *         of every View Presenters
 *         <p/>
 *         This is the main responsibility of NavigationController Class, nothing more; it is NOT aware of the Model, of
 *         each presenter;
 *         <p/>
 *         Each ViewImpl has a reference to this singleton NavigationController
 */

public final class NavigationControllerImpl implements NavigationController {

    // ------------ Singleton ---------------//
    private static NavigationController INSTANCE;

    // ----------- Declare and instantiate all registered Views -----------------//
    private AddressbookMainView mainView;
    private AddContactView addView;
    private HelpView helpView;
    private SearchContactView searchContactView;
    private ShowAllContactsView showAllContactsView;
    protected LoginPopupView loginView;
    private final Map<ViewType, DefaultView> registeredViews;

    private NavigationControllerImpl() {
        registeredViews = new ConcurrentHashMap<>();
    }

    public static NavigationController createInstance() {
        synchronized (NavigationControllerImpl.class) {
            if (INSTANCE == null) {
                INSTANCE = new NavigationControllerImpl();
            }
        }
        return INSTANCE;
    }

    @Override
    public void registerView(ViewType viewType, DefaultView view) {
        registeredViews.put(viewType, view);
    }

    @Override
    public void dispatch(MenuActionType pressedMenuAction) {

        initializeRegisteredViews();

        /*
           * For performance reasons we don't parse every single possible View but rather split the logic for menu Types
           */
        // ---------------------- the HorizontalMenu was pressed --------------------------//
        if (pressedMenuAction instanceof AddressboookHorizontalMenu) {

            if (pressedMenuAction == HorizontalMenuBarActions.ADD_CONTACT) {
                final AddContactPresenter presenter = new AddContactPresenter(mainView, addView, showAllContactsView);

                presenter.dispatchToAddContactView();
            } else if (pressedMenuAction == HorizontalMenuBarActions.DELETE_CONTACT) {
                final ShowAllContactsPresenter showContactPresenter = new ShowAllContactsPresenter(mainView, showAllContactsView);
                new DeleteContactPresenter(showAllContactsView);

                showContactPresenter.showForDeletionContacts();
                showContactPresenter.displayDeleteOption();

            } else if (pressedMenuAction == HorizontalMenuBarActions.EDIT_CONTACT) {
                final EditContactPresenter presenter = new EditContactPresenter();
                presenter.editContactEvent();

            } else if (pressedMenuAction == HorizontalMenuBarActions.SHARE_CONTACT) {
                final ShareContactPresenter presenter = new ShareContactPresenter();
                presenter.shareContactEvent();

            } else if (pressedMenuAction == HorizontalMenuBarActions.HELP_BUTTON) {
                final HelpPresenter presenter = new HelpPresenter(mainView, helpView);
                presenter.showHelpView();
            }

            // ------------------- the Left Vertical Menu was pressed ------------------------ //
        } else if (pressedMenuAction instanceof AddressbookVerticalMenu) {

            if (pressedMenuAction == VerticalMenuBarActions.SHOW_ALL_PROPERTY) {
                final ShowAllContactsPresenter presenter = new ShowAllContactsPresenter(mainView, showAllContactsView);
                presenter.showReadOnlyContacts();

            } else if (pressedMenuAction == VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY) {
                // tie together the SearchContactPresenter to the SearchContactView
                SearchContactView.SearchContactListener searchPresenter = new SearchContactPresenter(mainView,
                        searchContactView, showAllContactsView);

                searchPresenter.displaySearchContactView();
            }

        } // end of Menu Parse
    } // end of dispatch()

    private void initializeRegisteredViews() {

        // ----------- Retrieve the Registered Views of the Application -------------//
        mainView = (AddressbookMainView) registeredViews.get(ViewType.MAIN_VIEW);
        addView = (AddContactView) registeredViews.get(ViewType.ADD_CONTACT_VIEW);
        helpView = (HelpView) registeredViews.get(ViewType.HELP_VIEW);
        searchContactView = (SearchContactView) registeredViews.get(ViewType.SEARCH_CONTACT_VIEW);
        showAllContactsView = (ShowAllContactsView) registeredViews.get(ViewType.SHOW_CONTACT_VIEW);
        loginView = (LoginPopupView) registeredViews.get(ViewType.LOGIN_CONTACT_VIEW);
    }
}
