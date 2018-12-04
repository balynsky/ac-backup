package com.balynsky.ac.backup.service.impl;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.backup.service.BackupService;
import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.StorageResource;
import com.balynsky.ac.storage.clients.StorageClient;
import com.balynsky.ac.storage.exceptions.BadRequestException;
import com.balynsky.ac.user.UserResource;
import com.balynsky.ac.user.clients.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BackupServiceImpl implements BackupService {

	private final UserResource userResource;
	private final StorageResource storageResource;

	public BackupServiceImpl(UserClient userResource, StorageClient storageResource) {
		this.userResource = userResource;
		this.storageResource = storageResource;
	}

	@Override
	public SoulEntity backupSoul(SoulEntity soul) {
		final ResponseEntity<Client> userInfo = userResource.getUserInfo(soul.getClientId());
		if (userInfo.getBody() != null && userInfo.getBody().isMeth()) {
			try {
				return storageResource.saveSoul(soul);
			} catch (BadRequestException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}
}
