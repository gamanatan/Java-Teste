package com.example.api.commons;

import com.example.api.web.rest.form.CustomerForm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static CustomerForm createAndGetCustomerForm(String name, String email) {
		CustomerForm customerForm = new CustomerForm();
		customerForm.setName(name);
		customerForm.setEmail(email);
		
		return customerForm;
	}
}
