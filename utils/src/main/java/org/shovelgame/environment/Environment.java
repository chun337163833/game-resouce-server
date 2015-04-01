package org.shovelgame.environment;

public enum Environment {

	DEVELOPMENT("development"), PRODUCTION("production"), TEST("test");
	private String code;

	private Environment(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
