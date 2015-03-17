package com.nex.web.spring.controller.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nex.game.domain.Item;

@RestController
@RequestMapping("/wtf")
public class TestController {

	@RequestMapping
	public Item test() {
		return new Item();
	}
	
}
