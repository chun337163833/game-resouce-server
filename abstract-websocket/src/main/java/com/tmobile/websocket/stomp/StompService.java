package com.tmobile.websocket.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.tmobile.web.spring.controller.stomp.Result;

@Configurable
public class StompService {

	private SimpMessagingTemplate template;
	
	@Autowired
	public StompService(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	public void sendTo(String message) {
		 this.template.convertAndSend("/topic/push", new Result(message));
	}
	
}
