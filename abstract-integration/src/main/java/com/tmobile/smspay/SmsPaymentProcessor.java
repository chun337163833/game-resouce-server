package com.tmobile.smspay;

import java.util.Date;
import java.util.List;

public interface SmsPaymentProcessor {
	/**
	 * Initializes payment
	 * 
	 * @param requests list of sms requests
	 * @return list of responses - ok or bad according to application logic
	 */
	public List<InitResponse> initializePayments(List<InitRequest> requests);
	
	public String confirmPayment(ConfirmRequest request);
}
