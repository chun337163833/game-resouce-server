package com.nex.web.spring.controller.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestObject {

	private String text;
	public TestObject(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("improvedText")
	public String getImprovedText() {
		return this.text + " is improved";
	}
	
}
