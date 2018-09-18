package com.balynsky.ac.user.controller;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.user.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping(path = "/user-service")
public class ClientInfoController {

	private final ClientService clientService;

	public ClientInfoController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping(path = "/client/{id}")
	public ResponseEntity<Client> getClientInfo(@PathVariable Long id) {
		Client result = clientService.getClientInfo(id);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
}
