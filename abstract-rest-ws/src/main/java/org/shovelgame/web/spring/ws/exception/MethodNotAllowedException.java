package org.shovelgame.web.spring.ws.exception;

import org.shovelgame.environment.Environment;
import org.shovelgame.environment.EnvironmentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends EnvironmentException {
	
	private static final long serialVersionUID = 1L;
	
	public MethodNotAllowedException(Environment environment) {
		super(environment);
	}
}
