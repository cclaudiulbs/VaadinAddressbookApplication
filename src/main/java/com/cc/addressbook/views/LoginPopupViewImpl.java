package com.cc.addressbook.views;

import com.vaadin.Application;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 */

public class LoginPopupViewImpl extends CustomComponent
        implements LoginPopupView, Button.ClickListener {

    private final Button submitLogin = new Button("Submit");
    private final Button cancelLogin = new Button("Cancel");
    private final List<LoginPresenter> loggingPresenters = new ArrayList<>();
    private final Application mainApp;

    public LoginPopupViewImpl(Application mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void showView() {
    }

    @Override
    public void closeView() {

    }

    @Override
    public void addPresenter(LoginPresenter presenter) {
        loggingPresenters.add(presenter);
    }

    private ComponentContainer buildLoginPopupView() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(Boolean.TRUE);
        mainLayout.setMargin(Boolean.TRUE);

        final FormLayout loginForm = new FormLayout();

        final TextField email = new TextField("Email");
        final PasswordField password = new PasswordField("Password");
        final CheckBox rememberMe = new CheckBox("Remember Me");

        loginForm.addComponent(email);
        loginForm.addComponent(password);
        loginForm.addComponent(rememberMe);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponent(submitLogin);
        buttonLayout.addComponent(cancelLogin);

        PopupView popupView = new PopupView("", buildLoginPopupView());
        popupView.setPopupVisible(Boolean.TRUE);
        setCompositionRoot(popupView);
        mainApp.getMainWindow().getContent().addComponent(this);


        mainLayout.addComponent(loginForm);
        mainLayout.addComponent(buttonLayout);
        mainLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

        return mainLayout;
    }

    /**
     * Vaadin related Events for Logging In
     *
     * @param event
     */
    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getButton() == submitLogin) {
            // do call presenter for validating and processing via Model the Login
        } else if (event.getButton() == cancelLogin) {
            // do call presenter for closing this popup login view
        }
    }
}

//Window loginWindow = new Window();
//loginWindow.center();
//loginWindow.setModal(Boolean.TRUE);
//loginWindow.setClosable(Boolean.FALSE);
//loginWindow.setResizable(Boolean.FALSE);
//loginWindow.setDraggable(Boolean.FALSE);
//loginWindow.setWidth(500, Sizeable.UNITS_PIXELS);
//loginWindow.setHeight(null);
//
//loginWindow.setContent(buildLoginPopupView());
//
//mainApp.getMainWindow().addWindow(loginWindow);