package com.balynsky.ac.backup.config;

import com.balynsky.ac.storage.StorageResource;
import com.balynsky.ac.storage.impl.StorageResourceImpl;
import com.balynsky.ac.user.ClientResource;
import com.balynsky.ac.user.impl.ClientResourceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestSeviceConfig {

	@Value("${resource.client}")
	private String clientResource;

	@Value("${resource.storage}")
	private String storageResource;

	@Bean
	public ClientResource provideClientResource() {
		return new ClientResourceImpl(new RestTemplate(), clientResource);
	}

	@Bean
	public StorageResource provideStorageResource() {
		return new StorageResourceImpl(new RestTemplate(), storageResource);
	}

}
