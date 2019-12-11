package com.example.api.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.api.service.BuscaCepService;

@RestController
@RequestMapping("/enderecos")
public class BuscaCepResource {

	private static final RestTemplate Endereco = null;
	@Autowired
	private BuscaCepService buscaCepService;
	
	public String find() {
		buscaCepService.find(Endereco);
		return Endereco.toString();
	}
}