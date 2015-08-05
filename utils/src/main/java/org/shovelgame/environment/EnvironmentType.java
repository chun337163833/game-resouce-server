package org.shovelgame.environment;

public enum EnvironmentType {

	DEVELOPMENT("development"), PRODUCTION("production"), TEST("test");
	private String code;

	private EnvironmentType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
