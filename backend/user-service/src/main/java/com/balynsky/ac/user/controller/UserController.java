package com.balynsky.ac.user.controller;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.user.UserResource;
import com.balynsky.ac.user.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserResource {

	private final ClientService clientService;

	public UserController(ClientService clientService) {
		this.clientService = clientService;
	}

	public ResponseEntity<Client> getUserInfo(@PathVariable("id") Long id) {
		Client result = clientService.getClientInfo(id);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
}
