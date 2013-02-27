package com.cc.addressbook.util;

import com.cc.addressbook.views.SearchContactView;

/**
 * @author cclaudiu
 * <br/><br/>
 * Use the BuilderPattern to help build the SearchCriteria CLass
 * A Convenient Thread-Safe way to build partial or all the properties of the class
 * <br/>
 * Used in the {@link SearchContactView}
 */

public class SearchCriteria {
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public static class Builder {
		private String firstName;
		private String lastName;
		private String phoneNumber;
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public SearchCriteria build() {
			return new SearchCriteria(firstName, lastName, phoneNumber);
		}
	}
	
	private SearchCriteria(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
}