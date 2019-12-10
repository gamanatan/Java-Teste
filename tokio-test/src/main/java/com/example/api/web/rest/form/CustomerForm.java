package com.example.api.web.rest.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.api.domain.Customer;

public class CustomerForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer converter() {
		Customer customer = new Customer();
		customer.setName(this.name);
		customer.setEmail(this.email);
		return customer;
	}

	public Customer update(Customer customer) {
		customer.setName(this.name);
		customer.setEmail(this.email);
		return customer;
	}

}
