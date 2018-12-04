package com.balynsky.ac.storage;

import com.balynsky.ac.storage.exceptions.BadRequestException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/storage-service")
public interface StorageResource {

	@RequestMapping(value = "storage", method = RequestMethod.POST)
	SoulEntity saveSoul(@RequestBody SoulEntity soul) throws BadRequestException;
}
