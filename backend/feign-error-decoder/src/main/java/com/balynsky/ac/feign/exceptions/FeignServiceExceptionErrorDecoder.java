package com.balynsky.ac.feign.exceptions;

import feign.RequestLine;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class FeignServiceExceptionErrorDecoder implements ErrorDecoder {
	private Class<?> apiClass;
	private Map<String, ThrownServiceExceptionDetails> exceptionsThrown = new HashMap<>();
	private ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

	public FeignServiceExceptionErrorDecoder(Class apiClass) throws Exception {
		this.apiClass = apiClass;
		for (Method method : apiClass.getMethods()) {
			if (checkMappingAnnotations(method)) {
				for (Class clazz : method.getExceptionTypes()) {
					if (ServiceException.class.isAssignableFrom(clazz)) {
						if (Modifier.isAbstract(clazz.getModifiers())) {
							extractServiceExceptionInfoFromSubClasses(clazz);
						} else {
							extractServiceExceptionInfo(clazz);
						}
					} else {
						log.info("Exception '{}' declared thrown on interface '{}' doesn't inherit from " +
								"ServiceException, it will be skipped.", clazz.getName(), apiClass.getName());
					}
				}
			}
		}
	}

	private boolean checkMappingAnnotations(Method method) {
		return method.getAnnotation(PostMapping.class) != null ||
				method.getAnnotation(GetMapping.class) != null ||
				method.getAnnotation(PutMapping.class) != null ||
				method.getAnnotation(DeleteMapping.class) != null ||
				method.getAnnotation(PatchMapping.class) != null ||
				method.getAnnotation(RequestMapping.class) != null ||
				method.getAnnotation(RequestLine.class) != null;
	}

	private void extractServiceExceptionInfo(Class<?> clazz) throws Exception {
		ServiceException thrownException = null;
		Constructor<?> emptyConstructor = null;
		Constructor<?> messageConstructor = null;

		for (Constructor<?> constructor : clazz.getConstructors()) {
			Class<?>[] parameters = constructor.getParameterTypes();
			if (parameters.length == 0) {
				emptyConstructor = constructor;
				thrownException = (ServiceException) constructor.newInstance();
			} else if (parameters.length == 1 && parameters[0].isAssignableFrom(String.class)) {
				messageConstructor = constructor;
				thrownException = (ServiceException) constructor.newInstance(new String());
			}
		}

		if (thrownException != null) {
			exceptionsThrown.put(thrownException.getErrorCode(),
					new ThrownServiceExceptionDetails((Class<? extends ServiceException>) clazz,
							(Constructor<? extends ServiceException>) emptyConstructor,
							(Constructor<? extends ServiceException>) messageConstructor));
		} else {
			log.warn("Couldn't instantiate the exception '{}' for the interface '{}', it needs an empty or String " +
					"only *public* constructor.", clazz.getName(), apiClass.getName());
		}
	}

	private void extractServiceExceptionInfoFromSubClasses(Class<?> clazz) throws Exception {
		Set<Class<?>> subClasses = getAllSubClasses(clazz);
		for (Class<?> subClass : subClasses) {
			extractServiceExceptionInfo(subClass);
		}
	}

	private Set<Class<?>> getAllSubClasses(Class<?> clazz) throws ClassNotFoundException {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AssignableTypeFilter(clazz));

		Set<BeanDefinition> components = provider.findCandidateComponents("your/base/package/here");

		Set<Class<?>> subClasses = new HashSet<>();
		for (BeanDefinition component : components) {
			subClasses.add(Class.forName(component.getBeanClassName()));
		}
		return subClasses;
	}

	@Override
	public Exception decode(String methodKey, Response response) {
		JacksonDecoder jacksonDecoder = new JacksonDecoder();
		try {
			RestException restException = (RestException) jacksonDecoder.decode(response, RestException.class);
			if (restException != null && exceptionsThrown.containsKey(restException.getErrorCode())) {
				return getExceptionByReflection(restException);
			}
		} catch (IOException e) {
			// Fail silently here, irrelevant as a new exception will be thrown anyway
		} catch (Exception e) {
			log.error("Error instantiating the exception to be thrown for the interface '{}'", apiClass.getName(), e);
		}
		return defaultErrorDecoder.decode(methodKey, response); //fallback not presented here
	}

	private ServiceException getExceptionByReflection(RestException restException) throws Exception {
		ServiceException exceptionToBeThrown;
		ThrownServiceExceptionDetails exceptionDetails = exceptionsThrown.get(restException.getErrorCode());
		if (exceptionDetails.getMessageConstructor() != null) {
			exceptionToBeThrown = exceptionDetails.getMessageConstructor().newInstance(restException.getMessage());
		} else {
			exceptionToBeThrown = exceptionDetails.getEmptyConstructor().newInstance();
		}
		return exceptionToBeThrown;

	}
}
