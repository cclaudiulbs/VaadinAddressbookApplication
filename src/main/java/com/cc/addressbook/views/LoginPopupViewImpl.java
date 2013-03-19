package com.cc.addressbook.views;

import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cclaudiu
 *         <p/>
 *         LoginView will Events are handled by LoginPresenter which should process the login
 */

public class LoginPopupViewImpl extends CustomComponent
        implements LoginPopupView, Button.ClickListener {

    private final Button submitLogin = new Button("Submit");
    private final Button cancelLogin = new Button("Cancel");

    private final TextField email = new TextField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final CheckBox rememberMe = new CheckBox("Remember Me");

    private final List<LoginPresenter> loggingPresenters = new ArrayList<>();
    private final Application mainAppInstance;

    public LoginPopupViewImpl(Application mainAppInstance) {
        this.mainAppInstance = mainAppInstance;
    }

    /**
     * Login Button is clicked -- this is the actual Modal Window dispatched to User
     */
    @Override
    public void showView() {
        mainAppInstance.getMainWindow().addWindow(buildLoginWindow());
    }

    @Override
    public void closeView() {

    }

    @Override
    public void addPresenter(LoginPresenter presenter) {
        loggingPresenters.add(presenter);
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
            ContactNotificationUtil.showTrayNotification("Trying to Authenticate!", mainAppInstance);

        } else if (event.getButton() == cancelLogin) {
            // do call presenter for closing this popup login view
            ContactNotificationUtil.showTrayNotification("Canceled Login!", mainAppInstance);

        }
    }

    private Window buildLoginWindow() {
        Window loginWindow = new Window();

        loginWindow.setContent(buildLoginWindowLayout());
        customizeLoginWindow(loginWindow);

        return loginWindow;
    }

    private void customizeLoginWindow(Window loginWindow) {
        loginWindow.center();
        loginWindow.setModal(Boolean.TRUE);
//        loginWindow.setClosable(Boolean.FALSE);
        loginWindow.setResizable(Boolean.FALSE);
        loginWindow.setDraggable(Boolean.FALSE);
        loginWindow.setWidth(500, Sizeable.UNITS_PIXELS);
        loginWindow.setHeight(300, Sizeable.UNITS_PIXELS);
    }

    private ComponentContainer buildLoginWindowLayout() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
//        mainLayout.setWidth(300, Sizeable.UNITS_PIXELS);
//        mainLayout.setHeight(300, Sizeable.UNITS_PIXELS);
        mainLayout.setSpacing(Boolean.TRUE);
        mainLayout.setMargin(Boolean.TRUE);

        final FormLayout loginForm = new FormLayout();

        // ----------- Enable Buffering ---------------//
        email.setWriteThrough(Boolean.FALSE);
        password.setWriteThrough(Boolean.FALSE);
        rememberMe.setWriteThrough(Boolean.FALSE);

        loginForm.addComponent(email);
        loginForm.addComponent(password);
        loginForm.addComponent(rememberMe);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(Boolean.TRUE);
        buttonLayout.addComponent(submitLogin);
        buttonLayout.addComponent(cancelLogin);

        // ------ Add Listeners to Login Window buttons -------//
        submitLogin.addListener(this);
        cancelLogin.addListener(this);

        mainLayout.addComponent(loginForm);
        mainLayout.addComponent(buttonLayout);
        mainLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

        return mainLayout;
    }
}
