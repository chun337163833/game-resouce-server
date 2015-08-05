package org.shovelgame.environment;


public class EnvironmentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EnvironmentException(EnvironmentType environment) {
		super("Method not allowed in environment: " + environment);
	}
}
