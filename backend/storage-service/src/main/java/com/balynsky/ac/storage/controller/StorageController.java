package com.balynsky.ac.storage.controller;

import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.StorageResource;
import com.balynsky.ac.storage.exceptions.BadRequestException;
import com.balynsky.ac.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/storage-service")
public class StorageController implements StorageResource {
	private final StorageService storageService;

	@Autowired
	public StorageController(StorageService storageService) {
		this.storageService = storageService;
	}

	@PostMapping(path = "/storage")
	public SoulEntity saveSoul(@RequestBody SoulEntity entity) throws BadRequestException {
		return storageService.saveSoul(entity);
	}

}
