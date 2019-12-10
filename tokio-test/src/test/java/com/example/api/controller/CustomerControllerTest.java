package com.example.api.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.api.commons.Util;
import com.example.api.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	private static final String URL_DEFAULT = "/customers";
	private static final String URL_CUSTOMER_ONE = URL_DEFAULT + "/1"; 
	private static final String URL_UPDATE = URL_DEFAULT + "/2"; 
	private static final String URL_DELETE = URL_DEFAULT + "/3"; 
	private static final String URL_NOT_FOUND = URL_DEFAULT + "/100"; 

	@Autowired
	public WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testFindAll() throws Exception {
		this.mvc.perform(get(URL_DEFAULT))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content", hasSize(10)));
		
	}
	
	@Test
	public void testFindByIdBySucess() throws Exception {
		this.mvc.perform(get(URL_CUSTOMER_ONE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("name", equalTo("Aline Silva")));

	}
	

	@Test
	public void testFindByIdByNotFound() throws Exception {
		this.mvc.perform(get(URL_NOT_FOUND))
			.andExpect(status().isNotFound());

	}
	
	@Test
	public void testCreateSuccess() throws Exception {
		Customer customer = Util.createAndGetCustomerForm("Aline Silva", "aline_silva@email.com").converter();
		
		this.mvc.perform(post(URL_DEFAULT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Util.asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void testCreateBadRequest() throws Exception {
		Customer customer = Util.createAndGetCustomerForm("Aline Silva", "aline_silva@").converter();
		
		this.mvc.perform(post(URL_DEFAULT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Util.asJsonString(customer)))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateSuccess() throws Exception {
		Customer customer = Util.createAndGetCustomerForm("Aline Silva Updated", "aline_silva@email.com").converter();
		
		this.mvc.perform(put(URL_UPDATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Util.asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("name", equalTo("Aline Silva Updated")));
	}
	
	@Test
	public void testDelete() throws Exception {
		this.mvc.perform(delete(URL_DELETE))
			.andExpect(status().isOk());
	}
}
