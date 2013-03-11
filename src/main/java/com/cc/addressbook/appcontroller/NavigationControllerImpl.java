package com.cc.addressbook.appcontroller;

import com.cc.addressbook.menu.actions.HorizontalMenuBarActions;
import com.cc.addressbook.menu.actions.VerticalMenuBarActions;
import com.cc.addressbook.menu.types.AddressbookVerticalMenu;
import com.cc.addressbook.menu.types.AddressboookHorizontalMenu;
import com.cc.addressbook.menu.types.MenuActionType;
import com.cc.addressbook.presenters.*;
import com.cc.addressbook.views.AddContactView;
import com.cc.addressbook.views.AddressbookMainView;
import com.cc.addressbook.views.DefaultView;
import com.cc.addressbook.views.SearchContactView;
import com.cc.addressbook.views.ShowAllContactsView;
import com.cc.addressbook.views.types.ViewType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cclaudiu
 *         <br><br>
 *         NavigationController is the First Entry Point into the Addressbook
 *         application It controls the UI View Flow/Navigation by delegating to
 *         the corresponding View Presenter This being said, is aware, has
 *         compositions of every View Presenters
 *         <p/>
 *         This is the main responsibility of NavigationController Class,
 *         nothing more; it is NOT aware of the Model, of each presenter;
 *         <p/>
 *         Each ViewImpl has a reference to this singleton NavigationController
 *
 *         TODO: refactor this, to retrieve, and initialize all the registered Views in ONLY one place
 */

public final class NavigationControllerImpl implements NavigationController {

    private static NavigationController INSTANCE;

    private NavigationControllerImpl() {
    }

    public static NavigationController createInstance() {
        synchronized (NavigationControllerImpl.class) {
            if (INSTANCE == null) {
                INSTANCE = new NavigationControllerImpl();
            }
        }
        return INSTANCE;
    }

    private final Map<ViewType, DefaultView> registeredViews = new ConcurrentHashMap<>();

    @Override
    public void registerView(ViewType viewType, DefaultView view) {
        registeredViews.put(viewType, view);
    }

    @Override
    public void dispatch(MenuActionType pressedMenuAction) {

        /*
         * For performance reasons we don't parse every single possible View but
         * rather split the logic for menu Types
         */
        // ---------------------- the HorizontalMenu was pressed --------------------------//
        if (pressedMenuAction instanceof AddressboookHorizontalMenu) {

            if (pressedMenuAction == HorizontalMenuBarActions.ADD_CONTACT) {
                final AddressbookMainView mainView = (AddressbookMainView) registeredViews.get(ViewType.MAIN_VIEW);
                final AddContactView addView = (AddContactView) registeredViews.get(ViewType.ADD_CONTACT_VIEW);
                final ShowAllContactsView showView = (ShowAllContactsView) registeredViews.get(ViewType.SHOW_CONTACT_VIEW);

                final AddContactPresenter presenter = new AddContactPresenter(mainView, addView, showView);

                presenter.dispatchToAddContactView();

            } else if (pressedMenuAction == HorizontalMenuBarActions.EDIT_CONTACT) {
                final EditContactPresenter presenter = new EditContactPresenter();
                presenter.editContactEvent();

            } else if (pressedMenuAction == HorizontalMenuBarActions.SHARE_CONTACT) {
                final ShareContactPresenter presenter = new ShareContactPresenter();
                presenter.shareContactEvent();

            } else if (pressedMenuAction == HorizontalMenuBarActions.HELP_BUTTON) {
                final HelpPresenter presenter = new HelpPresenter();
                presenter.helpPresenter();

            }

            // ------------------- the Left Vertical Menu was pressed ------------------------ //
        } else if (pressedMenuAction instanceof AddressbookVerticalMenu) {

            if (pressedMenuAction == VerticalMenuBarActions.SHOW_ALL_PROPERTY) {
                final ShowAllContactsView showAllContactsView = (ShowAllContactsView) registeredViews.get(ViewType.SHOW_CONTACT_VIEW);
                final AddressbookMainView mainAppView = (AddressbookMainView) registeredViews.get(ViewType.MAIN_VIEW);

                final ShowAllContactsPresenter presenter = new ShowAllContactsPresenter(mainAppView, showAllContactsView);
                presenter.showContacts();

            } else if (pressedMenuAction == VerticalMenuBarActions.SEARCH_CONTACT_PROPERTY) {
                final AddressbookMainView mainView = (AddressbookMainView) registeredViews.get(ViewType.MAIN_VIEW);
                final SearchContactView searchContactView = (SearchContactView) registeredViews.get(ViewType.SEARCH_CONTACT_VIEW);
                final ShowAllContactsView showAllContactsView = (ShowAllContactsView) registeredViews.get(ViewType.SHOW_CONTACT_VIEW);

                // tie together the SearchContactPresenter to the SearchContactView
                SearchContactView.SearchContactListener searchPresenter = new SearchContactPresenter(mainView, searchContactView, showAllContactsView);

                searchPresenter.displaySearchContactView();
            }

        } // end of Menu Parse
    } // end of dispatch()
}