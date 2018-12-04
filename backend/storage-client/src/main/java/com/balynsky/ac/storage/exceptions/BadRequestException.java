package com.balynsky.ac.storage.exceptions;

import com.balynsky.ac.feign.exceptions.ServiceException;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ServiceException implements Serializable {
	public BadRequestException(final String message) {
		super(message, "BAD_REQUEST_EXCEPTION");
	}

}
