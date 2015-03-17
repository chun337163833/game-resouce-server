package com.nex.web.spring.controller.ws;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestObject {

	private NestetObject obj;

	private String text;

	public TestObject() {
		// TODO Auto-generated constructor stub
	}

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

	public NestetObject getObj() {
		return obj;
	}

	public void setObj(NestetObject obj) {
		this.obj = obj;
	}

}
