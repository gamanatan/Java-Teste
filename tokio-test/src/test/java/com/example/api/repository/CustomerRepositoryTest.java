package com.example.api.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerService customerService;

	@Test
	public void testCustomerFindAll() {
		Page<Customer> customers = customerService.findAll(0, 10);
		Customer customer = customers.getContent().get(0);
		assertEquals("Aline Silva", customer.getName());
	}

}
