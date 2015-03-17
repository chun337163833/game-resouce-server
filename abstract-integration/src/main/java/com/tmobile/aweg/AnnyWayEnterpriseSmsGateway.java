package com.tmobile.aweg;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.tmobile.annotation.Logger;
import com.tmobile.domain.sms.SmsMessage;
import com.tmobile.http.services.HttpClientAdapter;
import com.tmobile.http.services.HttpClientAdapter.HttpResult;
import com.tmobile.messaging.sms.SmsThrottlingException;
import com.tmobile.messaging.sms.UndeliverableSmsException;
import com.tmobile.spring.integration.sms.SmsRelayer;
import com.tmobile.spring.services.telephony.PhoneUtils;

@Logger
public class AnnyWayEnterpriseSmsGateway implements SmsRelayer {
	private static final String PARAM_AUTH_NAME = "auth";
	private static final String PARAM_SMSTEXT_NAME = "smstext";
	private static final String PARAM_RECEIVER_NAME = "receiver";

	@Resource(name = "smsGateWayClient")
	HttpClientAdapter httpAdapter;
	
	@Value("${smssender.auth}")
	private String authToken;
	
	@Autowired
	PhoneUtils phoneUtils;
	
	@Value("${smssender.gatewayurl}")
	private String gatewayUrl;
	
	
	@Override
	public long relaySms(SmsMessage smsMessage) 
			throws SmsThrottlingException, UndeliverableSmsException {
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_AUTH_NAME, authToken);
		params.put(PARAM_SMSTEXT_NAME, smsMessage.getMessage());
		params.put(PARAM_RECEIVER_NAME, phoneUtils.numberToStringE161(smsMessage.getPhoneNumber()));
		
		HttpClientAdapter.HttpResult r = null;
		
		try {
			r = httpAdapter.Get(gatewayUrl, params);
			log.debug(String.format("received aweg http response %d\n%s", r.getCode(), r.getResponse()));
		} catch (Exception e) {
			log.error("cannot send sms message", e);
			//in case of generic exception, try to redeliver later
			return -1;
		}
		
		return 0;
	}
}
