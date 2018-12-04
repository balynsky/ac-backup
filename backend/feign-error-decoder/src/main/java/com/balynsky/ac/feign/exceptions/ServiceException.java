package com.balynsky.ac.feign.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties(value = {"stackTrace", "localizedMessage", "suppressed", "cause"})
public abstract class ServiceException extends Exception {
	@NonNull
	private final String errorCode;

	public ServiceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public ServiceException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
