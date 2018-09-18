package com.balynsky.ac.user.impl;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.user.ClientResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ClientResourceImpl implements ClientResource {

	private final RestTemplate template;
	private final String baseUrl;

	public ClientResourceImpl(RestTemplate template, String baseUrl) {
		this.template = template;
		this.baseUrl = baseUrl;
	}

	@Override
	public Client getClientInfo(Long id) {
		String userResourceUrl = baseUrl + "/user-service/client/";
		ResponseEntity<Client> response = template.getForEntity(userResourceUrl + id, Client.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		return response.getBody();
	}
}
