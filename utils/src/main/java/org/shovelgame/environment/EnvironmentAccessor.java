package org.shovelgame.environment;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

@Configurable
public class EnvironmentAccessor { //NOSONAR
	
	@Value("${environment:development}")
	private String environment;

	private static EnvironmentAccessor instance;
	
	static {
		EnvironmentAccessor.instance = new EnvironmentAccessor();
	}
	
	public static EnvironmentType getEnvironment() {
		return EnvironmentType.valueOf(EnvironmentAccessor.instance.environment.toUpperCase());
	}

}
