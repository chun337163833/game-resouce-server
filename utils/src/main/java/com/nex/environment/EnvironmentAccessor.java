package com.nex.environment;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

@Configurable
public class EnvironmentAccessor {
	
	@Value("${environment}")
	private String environment;

	private static EnvironmentAccessor instance;
	
	static {
		EnvironmentAccessor.instance = new EnvironmentAccessor();
	}
	
	public static Environment getEnvironment() {
		return Environment.valueOf(EnvironmentAccessor.instance.environment.toUpperCase());
	}

}
