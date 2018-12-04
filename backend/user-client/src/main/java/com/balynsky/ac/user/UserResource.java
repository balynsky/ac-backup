package com.balynsky.ac.user;

import com.balynsky.ac.backup.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/user-service")
public interface UserResource {
	@GetMapping(path = "/client/{id}")
	ResponseEntity<Client> getUserInfo(@PathVariable("id") Long id);
}
