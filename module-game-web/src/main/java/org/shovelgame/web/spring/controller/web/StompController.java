package org.shovelgame.web.spring.controller.web;

import org.shovelgame.websocket.stomp.StompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stomp")
public class StompController {
	
	@Autowired
	private StompService service;
	
	@RequestMapping
	public String showView() {
		return "test/stomp";
	}
	@RequestMapping("/send")
	public void send(@RequestParam String message) {
		this.service.sendTo(message);
	}
}
