package org.shovelgame.web.spring.controller.stomp;

public class Message {

	private String value;

	public Message(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
