package com.balynsky.ac.storage.clients;

import com.balynsky.ac.feign.exceptions.FeignServiceExceptionErrorDecoder;
import com.balynsky.ac.storage.StorageResource;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

/**
 */
@FeignClient(value = "storage", configuration = StorageClient.StorageClientConfiguration.class)
public interface StorageClient extends StorageResource {
	class StorageClientConfiguration {
		@Bean
		public ErrorDecoder provideErrorDecoder() throws Exception {
			return new FeignServiceExceptionErrorDecoder(StorageClient.class);
		}
	}
}
