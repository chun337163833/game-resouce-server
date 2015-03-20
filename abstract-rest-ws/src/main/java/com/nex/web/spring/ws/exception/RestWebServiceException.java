package com.nex.web.spring.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.nex.domain.common.JsonObject;

public class RestWebServiceException extends HttpStatusCodeException {

	private static final long serialVersionUID = 3307374860512044591L;
	
	private JsonObject response;
	
	public RestWebServiceException(HttpStatus statusCode, JsonObject json) {
		super(statusCode);
		this.response = json;
	}

	public JsonObject getResponse() {
		return response;
	}
}


