package com.tmobile.messaging.sms;

public class SmsThrottlingException extends Exception {
	private long requiredDelayInMsec = 0;

	public long getRequiredDelayInMsec() {		
		return requiredDelayInMsec;
	}

	public SmsThrottlingException(long requiredDelayInMsec) {
		this.requiredDelayInMsec = requiredDelayInMsec;
	}

	public SmsThrottlingException(long requiredDelayInMsec, String message, Throwable cause) {
		super(message, cause);
		this.requiredDelayInMsec = requiredDelayInMsec;
	}

	public SmsThrottlingException(long requiredDelayInMsec, String message) {
		super(message);		
		this.requiredDelayInMsec = requiredDelayInMsec;
	}
}
