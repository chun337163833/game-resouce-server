package com.nex.web.spring.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nex.environment.Environment;
import com.nex.environment.EnvironmentException;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends EnvironmentException {
	
	private static final long serialVersionUID = 1L;
	
	public MethodNotAllowedException(Environment environment) {
		super(environment);
	}
}
