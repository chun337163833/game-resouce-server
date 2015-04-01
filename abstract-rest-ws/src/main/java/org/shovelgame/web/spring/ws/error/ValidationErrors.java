package org.shovelgame.web.spring.ws.error;

import java.util.ArrayList;
import java.util.List;

import org.shovelgame.domain.common.JsonObject;

public class ValidationErrors implements JsonObject {

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
