package com.nex.web.spring.controller.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestWs {

	@RequestMapping
	public TestObject test() {
		return new TestObject("hello");
	}
	
}
