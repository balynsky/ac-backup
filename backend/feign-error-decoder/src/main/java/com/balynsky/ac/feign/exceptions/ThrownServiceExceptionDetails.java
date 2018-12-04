package com.balynsky.ac.feign.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Constructor;


@Data
@AllArgsConstructor
public class ThrownServiceExceptionDetails {
	private Class<? extends ServiceException> clazz;
	private Constructor<? extends ServiceException> emptyConstructor;
	private Constructor<? extends ServiceException> messageConstructor;
}
