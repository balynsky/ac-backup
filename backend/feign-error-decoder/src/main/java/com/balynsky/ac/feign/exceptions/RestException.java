package com.balynsky.ac.feign.exceptions;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestException implements Serializable {
	private String errorCode;
	private String message;
}
