package com.tmobile.web.spring.controller.ws.error;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.tmobile.domain.common.JsonResponse;
import com.tmobile.web.spring.ws.exception.RestWebServiceException;

@ControllerAdvice
public class WebServiceExceptionController {

	@ExceptionHandler({ HttpStatusCodeException.class })
	public void handleStatusCode(HttpStatusCodeException e, HttpServletResponse response) {
		response.setStatus(e.getStatusCode().value());
	}
	
	@ExceptionHandler({ RestWebServiceException.class })
	public @ResponseBody JsonResponse handleValidationErrorsRequest(RestWebServiceException e, HttpServletResponse response) {
		response.setStatus(e.getStatusCode().value());
		return e.getResponse();
	}
	
	
	
}
