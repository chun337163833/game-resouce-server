package com.tmobile.smspay;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class InitResponse {
	
	public InitResponse() {
	}
	
	public InitResponse(InitRequest initRequest,
			InitResponseState responseState, int smsPrice, String responseText) {
		super();
		this.initRequest = initRequest;
		this.responseState = responseState;
		this.smsPrice = smsPrice;
		this.responseText = responseText;
	}

	private InitRequest initRequest;
	
	private InitResponseState responseState;
	
	private int smsPrice;
	
	private String responseText;
}
