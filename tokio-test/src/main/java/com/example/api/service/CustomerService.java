package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	public Page<Customer> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
		return repository.findAll(pageRequest);
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer save(Customer customer) {
		return repository.save(customer);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
