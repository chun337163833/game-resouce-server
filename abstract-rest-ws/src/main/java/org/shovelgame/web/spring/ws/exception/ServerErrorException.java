package org.shovelgame.web.spring.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends HttpStatusCodeException {
	private static final long serialVersionUID = 1L;

	public ServerErrorException(String statusText) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, statusText);
	}

}
