package org.shovelgame.websocket.stomp;

import org.shovelgame.web.spring.controller.stomp.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configurable
public class StompService {

	private SimpMessagingTemplate template;
	private String sendToPrefix = "push";
	
	@Autowired
	public StompService(SimpMessagingTemplate template) {
		this.template = template;
	}

	public void sendTo(String destination, String message) {
		this.sendTo(destination, new Message(message));
	}

	public void sendTo(String destination, Object payload) {
		this.template.convertAndSend(String.format("/%s/%s", sendToPrefix, destination), payload);
	}

	public void setSendToPrefix(String sendToPrefix) {
		this.sendToPrefix = sendToPrefix;
	}
}
