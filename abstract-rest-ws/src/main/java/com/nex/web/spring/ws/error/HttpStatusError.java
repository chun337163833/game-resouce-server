package com.nex.web.spring.ws.error;

public class HttpStatusError {

	private int code;

	public HttpStatusError(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
