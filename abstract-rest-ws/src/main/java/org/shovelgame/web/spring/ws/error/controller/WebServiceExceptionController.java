package org.shovelgame.web.spring.ws.error.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.shovelgame.annotation.Logger;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.web.spring.ws.error.HttpStatusError;
import org.shovelgame.web.spring.ws.exception.RestWebServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
@RequestMapping("/error/")
@Logger
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
	
	@ExceptionHandler(Exception.class)
	public void globalExceptionhandler(HttpServletResponse resp, Exception ex) {
		try {
			log.error("", ex);
			resp.sendError(500);
		} catch (IOException e) {
			log.error("", e);
		}
	}
	
	@RequestMapping("{code}")
	public HttpStatusError httpCatchedError(@PathVariable int code) {
		return new HttpStatusError(code);
	}
	
}
