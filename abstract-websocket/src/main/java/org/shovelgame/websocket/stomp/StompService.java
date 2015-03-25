package org.shovelgame.websocket.stomp;

import org.shovelgame.web.spring.controller.stomp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configurable
public class StompService {

	private SimpMessagingTemplate template;
	
	@Autowired
	public StompService(SimpMessagingTemplate template) { //NOSONAR
		this.template = template;
	}
	
	public void sendTo(String message) {
		 this.template.convertAndSend("/topic/push", new Result(message));
	}
	
}
