package com.tmobile.web.spring.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tmobile.environment.Environment;
import com.tmobile.environment.EnvironmentException;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends EnvironmentException {
	
	private static final long serialVersionUID = 1L;
	
	public MethodNotAllowedException(Environment environment) {
		super(environment);
	}
}
