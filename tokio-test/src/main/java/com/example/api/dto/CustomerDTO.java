package com.example.api.dto;

import com.example.api.domain.Customer;

public class CustomerDTO {

	private Long id;
	private String name;
	private String email;

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
