package com.tmobile.messaging.sms;

public enum SmsMessagePriority {
	LOW(0), NORMAL(1), HIGH(2);
	
	int code;
	
	public int getCode() {
		return code;
	}

	SmsMessagePriority(int code) {
		this.code = code;		
	}
}
