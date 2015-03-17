package com.tmobile.spring.integration.sms;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tmobile.annotation.Logger;
import com.tmobile.domain.sms.OutgoingSmsState;
import com.tmobile.domain.sms.SmsMessage;
import com.tmobile.messaging.sms.SmsMessageBuilder;
import com.tmobile.utils.CalUtils;

/**
 * Filter in spring integration, filters SmsMessages - removes old messages, not used directly
 * 
 * @author PsencikO
 *
 */
@Logger
public class SmsMessageFilter {
	@Value("${smssender.maxsmsageinseconds}")
	public int maxSmsAgeInSeconds;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private void discardOutgoingSms(SmsMessage message) {
		message.merge().setState(OutgoingSmsState.DISCARDED);
	}
	
	@Filter
	public boolean accept(Message<SmsMessage> message) {
		if(message.getHeaders().containsKey(SmsMessageBuilder.FIRST_FAILURE_TIME_HEADER)) {
			Calendar c = CalUtils.addSeconds(
					(Calendar) message.getHeaders().get(SmsMessageBuilder.FIRST_FAILURE_TIME_HEADER),
					maxSmsAgeInSeconds);
			if(c.before(new GregorianCalendar())) {
				log.warn(String.format("discarding SMS because of long age - %s", message.getPayload()));
				discardOutgoingSms(message.getPayload());
				return false;
			}
		}
		return true;
	}
}
