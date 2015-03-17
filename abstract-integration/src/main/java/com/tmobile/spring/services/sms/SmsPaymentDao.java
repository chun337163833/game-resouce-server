package com.tmobile.spring.services.sms;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tmobile.domain.smspayment.SmsPayment;

/**
 * DAO methods for {@link SmsPayment} entity
 * 
 * @author PsencikO
 *
 */
@Service
public class SmsPaymentDao {
	public SmsPayment findSmsPaymentNotRejected(long phone, Calendar paymentTime) {
		List<SmsPayment> rvs = SmsPayment.entityManager().createNamedQuery("findSmsPaymentNotRejected", SmsPayment.class)
				.setParameter("phone", phone).setParameter("paymentTime", paymentTime).getResultList();
		
		if(rvs.size()==0)
			return null;
		
		return rvs.get(0);
	}	
}
