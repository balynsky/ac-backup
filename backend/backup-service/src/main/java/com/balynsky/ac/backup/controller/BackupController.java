package com.balynsky.ac.backup.controller;

import com.balynsky.ac.backup.service.BackupService;
import com.balynsky.ac.storage.SoulEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/backup-service")
public class BackupController {

	private final BackupService service;

	public BackupController(BackupService service) {
		this.service = service;
	}

	@PostMapping(path = "/backup")
	public ResponseEntity<SoulEntity> backupSoul(@RequestBody SoulEntity soul) {
		final SoulEntity soulEntity = service.backupSoul(soul);
		return new ResponseEntity<>(soulEntity, soulEntity != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
	}

}
