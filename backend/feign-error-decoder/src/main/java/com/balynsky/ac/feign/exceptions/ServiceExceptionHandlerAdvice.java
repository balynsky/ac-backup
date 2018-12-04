package com.balynsky.ac.feign.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandlerAdvice {

	@ExceptionHandler({ServiceException.class})
	ResponseEntity<ServiceException> handle(ServiceException exception) {
		ResponseStatus status = exception.getClass().getAnnotation(ResponseStatus.class);
		return new ResponseEntity<ServiceException>(exception, status == null ? HttpStatus.BAD_REQUEST : status.code());
	}

}
