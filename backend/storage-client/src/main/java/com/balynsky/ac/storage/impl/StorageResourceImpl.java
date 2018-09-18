package com.balynsky.ac.storage.impl;

import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.StorageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StorageResourceImpl implements StorageResource {

	private final RestTemplate template;
	private final String baseUrl;

	public StorageResourceImpl(RestTemplate template, String baseUrl) {
		this.template = template;
		this.baseUrl = baseUrl;
	}

	@Override
	public SoulEntity saveSoul(SoulEntity soul) {
		String userResourceUrl = baseUrl + "/storage-service/storage";
		ResponseEntity<SoulEntity> response = template.postForEntity(userResourceUrl, soul, SoulEntity.class);
		if (response.getStatusCode() != HttpStatus.CREATED) {
			return null;
		}
		return response.getBody();

	}
}
