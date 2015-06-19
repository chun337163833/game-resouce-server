package com.shovelgame.async;

import org.springframework.scheduling.annotation.Async;

public class TestAsync {

	@Async
	public void doIt() {
		System.out.println("yeaj");
	}
	

}
