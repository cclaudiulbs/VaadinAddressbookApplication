package com.cc.addressbook.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SearchCriteriaBuilderPatternTest {
	
	@Test public void search_criteria_builder_test() {
		SearchCriteria criteria = new SearchCriteria.Builder()
										.firstName("claudiu")
										.lastName("cosar")
										.phoneNumber("072121")
										.build();
		
		assertThat(criteria.getFirstName(), is("claudiu"));
		assertThat(criteria.getLastName(), is("cosar"));
		assertThat(criteria.getPhoneNumber(), is("072121"));
	}
}