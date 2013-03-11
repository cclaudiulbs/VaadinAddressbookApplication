package com.cc.addressbook.util;

import com.cc.addressbook.entities.JobEntity;
import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.ui.TextField;

import java.util.Arrays;

/**
 * @author cclaudiu
 *
 */

public final class Convertor {
    private Convertor() { }

    public static final PersonEntity fromAddContactFormToPerson(TextField firstName, TextField lastName, TextField address, TextField email,
                                                   TextField jobTitle, TextField jobDescription, TextField phoneNumber,
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
}