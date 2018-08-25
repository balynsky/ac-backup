package com.balynsky.ac.backup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackupController {

	@PostMapping(path = "/backup-service/backup")
	public ResponseEntity backupSoul(String soul) {
		System.out.println("New Soul " + soul);
		return new ResponseEntity(HttpStatus.CREATED);
	}

}
