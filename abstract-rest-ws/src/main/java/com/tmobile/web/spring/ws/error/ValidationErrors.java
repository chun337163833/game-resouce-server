package com.tmobile.web.spring.ws.error;

import java.util.ArrayList;
import java.util.List;

import com.tmobile.domain.common.JsonResponse;

public class ValidationErrors implements JsonResponse {

	private static final long serialVersionUID = 1L;

	private List<FieldError> errors = new ArrayList<>();

	private String message;
	private String status;
	
	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
