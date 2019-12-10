package com.example.api.web.rest;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDTO;
import com.example.api.service.CustomerService;
import com.example.api.web.rest.form.CustomerForm;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public Page<Customer> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return service.findAll(page, size);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Optional<Customer> customer = service.findById(id);

		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerForm customerForm, UriComponentsBuilder uriBuilder) {
		Customer customer = customerForm.converter();
		service.save(customer);

		URI uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(new CustomerDTO(customer));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomerForm customerForm, @PathVariable Long id) {
		Optional<Customer> optional = service.findById(id);

		if (optional.isPresent()) {
			Customer customer = customerForm.update(optional.get());
			return ResponseEntity.ok(new CustomerDTO(customer));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Customer> optional = service.findById(id);

		if (optional.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
