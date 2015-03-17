package com.tmobile.messaging.sms;

import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import com.tmobile.domain.sms.SmsMessage;

public class SmsMessageBuilder {
	public static String FIRST_FAILURE_TIME_HEADER = "com.tmobile.messaging.sms.SmsMessage.FIRST_FAILURE_TIME"; 
	public static String DELAY_HEADER = "com.tmobile.messaging.sms.SmsMessage.DELAY";
	
	private SmsMessage smsMessage;
	private SmsMessagePriority prio = SmsMessagePriority.NORMAL;
	
	public static SmsMessageBuilder create(SmsMessage smsMessage) {
		return new SmsMessageBuilder(smsMessage);		
	}
	
	protected SmsMessageBuilder(SmsMessage smsMessage) {
		this.smsMessage = smsMessage;
	}
	
	public SmsMessageBuilder setPriority(SmsMessagePriority priority) {
		prio = priority;
		return this;
	}
	
	public Message<SmsMessage> build() {
		return MessageBuilder.withPayload(smsMessage).setHeader(
				IntegrationMessageHeaderAccessor.PRIORITY, prio.getCode()).build();
	}
}
