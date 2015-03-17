package com.tmobile.spring.integration.sms;

import com.tmobile.domain.sms.SmsMessage;
import com.tmobile.http.services.HttpClientAdapter;
import com.tmobile.messaging.sms.SmsThrottlingException;
import com.tmobile.messaging.sms.UndeliverableSmsException;

/**
 * Interface implemented for relaying SMS into the SMS centre that supports
 * messages throttling
 * 
 * @author PsencikO
 *
 */
public interface SmsRelayer {
	/**
	 * Relays sms message
	 * 
	 * @param smsMessage
	 * @return -1 = sms was not sent, 0 sms was sent, n > 0 sms was sent throttling recommended
	 * @throws SmsThrottlingException sender must throttle for time speficied in exception
	 * @throws UndeliverableSmsException cannot deliver this SMS and should not attempt
	 */
	public long relaySms(SmsMessage smsMessage) 
			throws SmsThrottlingException, UndeliverableSmsException;
}
