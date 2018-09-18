package com.balynsky.ac.storage.controller;

import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/storage-service")
public class StorageController {
	private final StorageService storageService;

	@Autowired
	public StorageController(StorageService storageService) {
		this.storageService = storageService;
	}

	@PostMapping(path = "/storage")
	public ResponseEntity<SoulEntity> saveSoul(@RequestBody SoulEntity entity) {
		final SoulEntity soulEntity = storageService.saveSoul(entity);
		return soulEntity == null
				? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(soulEntity, HttpStatus.CREATED);
	}

}
