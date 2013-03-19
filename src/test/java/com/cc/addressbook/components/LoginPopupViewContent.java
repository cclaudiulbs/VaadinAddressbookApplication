package com.cc.addressbook.components;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

/**
 * @author cclaudiu
 *
 */

public class LoginPopupViewContent implements PopupView.Content {

    private Button submitLogin = new Button("Submit");
    private Button cancelLogin = new Button("Cancel");

    @Override
    public String getMinimizedValueAsHTML() {
        return null;
    }

    @Override
    public Component getPopupComponent() {
        return buildLoginPopupView();
    }


    private ComponentContainer buildLoginPopupView() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth(300, Sizeable.UNITS_PIXELS);
        mainLayout.setHeight(500, Sizeable.UNITS_PIXELS);
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

        mainLayout.addComponent(loginForm);
        mainLayout.addComponent(buttonLayout);
        mainLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);
        mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_RIGHT);

        return mainLayout;
    }
}