package com.tmobile.spring.integration.sms;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tmobile.annotation.Logger;
import com.tmobile.domain.sms.OutgoingSmsState;
import com.tmobile.domain.sms.SmsMessage;
import com.tmobile.http.services.HttpClientAdapter;
import com.tmobile.messaging.sms.SmsMessageBuilder;
import com.tmobile.messaging.sms.SmsThrottlingException;
import com.tmobile.messaging.sms.UndeliverableSmsException;

/**
 * Class is receiver in spring integration, purpose is send queued messages to SMS centre, not used directly
 * 
 * @author PsencikO
 *
 */
@Logger
public class SmsMessageReceiver {
	@Resource(name = "errorChannel")
	MessageChannel errorChannel;

	@Value("${smssender.maxthrottlingdelayinmsec}")
	public long maxThrottlingDelayInMsec;

	@Value("${smssender.maxthrottlingwaitinmsec}")
	public int maxThrottlingWaitInMsec;
		
	@Value("${smssender.auth}")
	private String authParam;
	
	@Resource(name="smsRelayingBean")
	SmsRelayer relayer;

	private Calendar throttlingTime = null;

	private Object lock = new Object();

	private Calendar getThrottlingTime() {
		synchronized (lock) {
			if (throttlingTime == null)
				return null;
			return (Calendar) throttlingTime.clone();
		}
	}

	private void setThrottlingTime(Calendar throttlingTime) {
		synchronized (lock) {
			this.throttlingTime = throttlingTime;
		}
	}

	private long sendSmsMessage(SmsMessage smsMessage)
			throws SmsThrottlingException, UndeliverableSmsException {
		
		return relayer.relaySms(smsMessage);
		
//		if (smsMessage.getMessage().contains("error"))
//			throw new RuntimeException("chybicka");
//
//		if (smsMessage.getMessage().contains("thr"))
//			throw new SmsThrottlingException(7000);
//
//		if (smsMessage.getMessage().contains("und"))
//			throw new UndeliverableSmsException();
	}

	private static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l
					+ " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

	private void resendMessageWithDelay(Message<SmsMessage> smsMessage) {
		resendMessageWithDelay(smsMessage, null);
	}
	
	private void resendMessageWithDelay(Message<SmsMessage> smsMessage, Date delayUntil) {
		MessageBuilder<SmsMessage> mb = MessageBuilder.fromMessage(smsMessage);
		
		if (!smsMessage.getHeaders().containsKey(
				SmsMessageBuilder.FIRST_FAILURE_TIME_HEADER)) {
			mb.setHeader(SmsMessageBuilder.FIRST_FAILURE_TIME_HEADER, new GregorianCalendar());
		}
		if(delayUntil==null) {
			if(smsMessage.getHeaders().containsKey(SmsMessageBuilder.DELAY_HEADER))
				mb.removeHeader(SmsMessageBuilder.DELAY_HEADER);
		} else {
			mb.setHeader(SmsMessageBuilder.DELAY_HEADER, delayUntil);
		}
		
		errorChannel.send(mb.build());
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void discardOutgoingSms(SmsMessage message) {
		message.merge().setState(OutgoingSmsState.DISCARDED);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void confirmOutgoingSms(SmsMessage message) {
		message.merge().setState(OutgoingSmsState.SENT);
	}

	public void processSmsMessage(Message<SmsMessage> smsMessage) {
		long requiredDelay = 0;
		Exception exc = null;
		
		if(!(smsMessage.getPayload() instanceof SmsMessage)) {
			log.error(String.format("received incorrect instance %s - %s", smsMessage.getPayload().getClass(), smsMessage));
			return;
		}

		// do not send message if throttling is required
		Calendar tt = getThrottlingTime();
		if (tt != null && tt.after(new GregorianCalendar())) {
			log.info(String.format(
					"deferring sms message, waiting for throttling delay - %s",
					smsMessage.getPayload()));
			resendMessageWithDelay(smsMessage, tt.getTime());
			return;
		}

		try {
			requiredDelay = sendSmsMessage(smsMessage.getPayload());
			if(requiredDelay!=-1) {
				log.info(String.format("sms message sent ok - %s",
						smsMessage.getPayload()));
	
				// in case of database exceptions (changing state), do not redeliver
				// sms message
				try {
					confirmOutgoingSms(smsMessage.getPayload());
				} catch (Exception e) {
					log.error(String.format(
							"could not update sms database state - %s",
							smsMessage.getPayload()), e);
				}
			} else {
				log.error(
						String.format("cannot deliver sms message - %s",
								smsMessage.getPayload()));				
			}
		} catch (UndeliverableSmsException e) {
			log.error(
					String.format("cannot deliver sms message - %s",
							smsMessage.getPayload()), e);
			requiredDelay = 0;
			discardOutgoingSms(smsMessage.getPayload());
		} catch (SmsThrottlingException e) {
			requiredDelay = e.getRequiredDelayInMsec();
			exc = e;
		} catch (Exception e) {
			log.error(
					String.format("cannot deliver sms message - %s",
							smsMessage.getPayload()), e);
			exc = e;
		}
		
		if (exc != null || requiredDelay==-1) {
			if(requiredDelay > 0 && requiredDelay > maxThrottlingWaitInMsec) {
				log.warn(String.format("sms processing delayed for %d msec",
						safeLongToInt(Math.min(requiredDelay,
								maxThrottlingDelayInMsec))));
				Calendar c = new GregorianCalendar();
				c.add(Calendar.MILLISECOND, safeLongToInt(Math.min(
						requiredDelay, maxThrottlingDelayInMsec)));
				setThrottlingTime(c);				
				resendMessageWithDelay(smsMessage, c.getTime());				
			} else {
				//with default delay
				resendMessageWithDelay(smsMessage);
			}
		}

		if (requiredDelay > 0) {
			long actualDelay = Math.min(requiredDelay, maxThrottlingWaitInMsec);
			try {
				Thread.sleep(actualDelay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
