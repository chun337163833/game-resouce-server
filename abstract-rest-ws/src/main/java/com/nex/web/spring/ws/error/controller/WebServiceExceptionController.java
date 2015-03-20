package com.nex.web.spring.ws.error.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.nex.domain.common.JsonObject;
import com.nex.web.spring.ws.exception.RestWebServiceException;

@ControllerAdvice
public class WebServiceExceptionController {

	@ExceptionHandler({ HttpStatusCodeException.class })
	public void handleStatusCode(HttpStatusCodeException e, HttpServletResponse response) {
		response.setStatus(e.getStatusCode().value());
	}
	
	@ExceptionHandler({ RestWebServiceException.class })
	public @ResponseBody JsonObject handleValidationErrorsRequest(RestWebServiceException e, HttpServletResponse response) {
		response.setStatus(e.getStatusCode().value());
		return e.getResponse();
	}
	
	
	
}
