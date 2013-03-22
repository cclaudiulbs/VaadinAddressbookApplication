package com.cc.addressbook.views;

import java.util.ArrayList;
import java.util.List;

import com.cc.addressbook.pojo.UserSubject;
import com.cc.addressbook.util.AddressbookConvertor;
import com.cc.addressbook.util.ContactNotificationUtil;
import com.vaadin.Application;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

/**
 * @author cclaudiu
 *         <p/>
 *         LoginView will Events are handled by AuthenticatePresenter which should process the login
 */

public class LoginPopupViewImpl extends CustomComponent implements LoginPopupView, Button.ClickListener {

	private final Button submitLogin = new Button("Submit");
	private final Button cancelLogin = new Button("Cancel");

	private final TextField email = new TextField("Email");
	private final PasswordField password = new PasswordField("Password");
	private final CheckBox rememberMe = new CheckBox("Remember Me");

	private final List<AuthenticatePresenter> loggingPresenters = new ArrayList<>();
	private final Application mainAppInstance;
	private Window loginWindow;

	public LoginPopupViewImpl(Application mainAppInstance) {
		this.mainAppInstance = mainAppInstance;
	}

	/**
	 * Login Button is clicked -- this is the actual Modal Window dispatched to User (lazy initialization)
	 */
	@Override
	public void showView() {
		loginWindow = buildLoginWindow();
		mainAppInstance.getMainWindow().addWindow(loginWindow);
	}

	@Override
	public void closeView() {
		mainAppInstance.getMainWindow().removeWindow(loginWindow);
	}

	@Override
	public void addPresenter(AuthenticatePresenter presenter) {
		loggingPresenters.add(presenter);
	}

	/**
	 * Vaadin related Events for Logging In
	 * 
	 * @param event
	 */
	@Override
	public void buttonClick(Button.ClickEvent event) {
		for(AuthenticatePresenter eachAuthenticatePresenter : loggingPresenters) {
			if(event.getButton() == submitLogin) {
				// do call presenter for validating and processing via Model the Login
				ContactNotificationUtil.showTrayNotification("Trying to Authenticate!", mainAppInstance);

                final UserSubject userSubject = AddressbookConvertor.fromLoginFormToUserSubject(email, password, rememberMe);

				eachAuthenticatePresenter.login(userSubject);

                // TODO: Once the Authentication succeeded, MainApplication should listen to userChangedEvents, and set this new logged in user into application Layout
                mainAppInstance.setUser(userSubject);

			} else if(event.getButton() == cancelLogin) {
				// do call presenter for closing this popup login modal window
				ContactNotificationUtil.showTrayNotification("Canceled Login!", mainAppInstance);
				eachAuthenticatePresenter.cancelLogin();
			}

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
		loginWindow.setClosable(Boolean.FALSE);
		loginWindow.setResizable(Boolean.FALSE);
		loginWindow.setDraggable(Boolean.FALSE);
		loginWindow.setWidth(500, Sizeable.UNITS_PIXELS);
		loginWindow.setHeight(300, Sizeable.UNITS_PIXELS);
	}

	private ComponentContainer buildLoginWindowLayout() {
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(Boolean.TRUE);
		mainLayout.setMargin(Boolean.TRUE);

		final FormLayout loginForm = new FormLayout();

		// ----------- Enable Buffering ---------------//
		email.setWriteThrough(Boolean.FALSE);
		password.setWriteThrough(Boolean.FALSE);
		rememberMe.setWriteThrough(Boolean.FALSE);

		// ----------- Enable Validators ----------//
		addDefaultTextFieldValidatorOn(email);

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

	/** Enable Validators on input TextFields */
	private void addDefaultTextFieldValidatorOn(TextField textField) {
		textField.addValidator(new RegexpValidator("(\\w|\\d)+@\\.[a-zA-Z]{1,3}",
				"Email must be of Type: alphabetical or digital representation"));
	}
}
