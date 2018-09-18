package com.balynsky.ac.backup.service.impl;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.backup.service.BackupService;
import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.StorageResource;
import com.balynsky.ac.user.ClientResource;
import org.springframework.stereotype.Service;

@Service
public class BackupServiceImpl implements BackupService {

	private final ClientResource clientResource;
	private final StorageResource storageResource;

	public BackupServiceImpl(ClientResource clientResource, StorageResource storageResource) {
		this.clientResource = clientResource;
		this.storageResource = storageResource;
	}

	@Override
	public SoulEntity backupSoul(SoulEntity soul) {
		final Client clientInfo = clientResource.getClientInfo(soul.getClientId());
		if (clientInfo.isMeth()) {
			return storageResource.saveSoul(soul);
		}
		return null;
	}
}
