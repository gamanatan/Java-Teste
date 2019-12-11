package com.example.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Endereco;

@Service
public class BuscaCepService {

	
	public String find(RestTemplate restTemplate) {
		Endereco endereco = restTemplate.getForObject(
				"https://viacep.com.br/ws/06330000/json/", Endereco.class);
		return endereco.toString();
	}
}
