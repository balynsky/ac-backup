package com.balynsky.ac.storage.service.impl;

import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.repository.SoulRepository;
import com.balynsky.ac.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
	private final SoulRepository repository;

	@Autowired
	public StorageServiceImpl(SoulRepository repository) {
		this.repository = repository;
	}

	@Override
	public SoulEntity saveSoul(SoulEntity soulEntity) {
		return repository.saveSoul(soulEntity);
	}
}
