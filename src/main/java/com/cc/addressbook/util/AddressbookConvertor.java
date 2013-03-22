package com.cc.addressbook.util;

import java.util.Arrays;

import com.cc.addressbook.entities.JobEntity;
import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.pojo.UserSubject;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * @author cclaudiu
 * 
 */

public final class AddressbookConvertor {

	private AddressbookConvertor() {
	}

	public static final PersonEntity fromAddContactFormToPerson(TextField firstName, TextField lastName,
			TextField address, TextField email, TextField jobTitle, TextField jobDescription, TextField phoneNumber,
			TextField homeNumber) {
		PersonEntity contact = new PersonEntity();
		JobEntity job = new JobEntity();
		job.setJobTitle(jobTitle.getValue().toString());
		job.setJobDescription(jobDescription.getValue().toString());

		contact.setFirstName(firstName.getValue().toString());
		contact.setLastName(lastName.getValue().toString());
		contact.setEmail(email.getValue().toString());
		contact.setAddress(address.getValue().toString());
		contact.setJob(Arrays.asList(job));
		contact.setPhoneHomeNumber(homeNumber.getValue().toString());
		contact.setPhoneMobileNumber(phoneNumber.getValue().toString());

		return contact;
	}

	public static final UserSubject fromLoginFormToUserSubject(TextField emailField, PasswordField passwordField,
			CheckBox rememberMeCheck) {
        final String email = (String) emailField.getValue();
        final char[] password = ((String)(passwordField.getValue())).toCharArray();
        final Boolean rememberMe = rememberMeCheck.booleanValue();


        AssertThat.notNull(email);
        AssertThat.notNull(password);
        AssertThat.notEmptyPassword(password);

        return new UserSubject(email, password, rememberMe);
	}
}
