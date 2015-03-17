package com.nex.web.spring.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.nex.domain.common.JsonResponse;

public class RestWebServiceException extends HttpStatusCodeException {

	private static final long serialVersionUID = 3307374860512044591L;
	
	private JsonResponse response;
	
	public RestWebServiceException(HttpStatus statusCode, JsonResponse json) {
		super(statusCode);
		this.response = json;
	}

	public JsonResponse getResponse() {
		return response;
	}
}


