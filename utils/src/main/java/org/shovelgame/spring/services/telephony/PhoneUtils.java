package org.shovelgame.spring.services.telephony;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Utilities for formating phone numbers
 * 
 * @author PsencikO
 *
 */
@Service
public class PhoneUtils {
	@Value("${phone.defaultPrefix}")
	public String defaultPrefix;
	
	public long formatNumber(long phoneNumber) {
		return formatNumberDefault(defaultPrefix, phoneNumber);
	}
	
	public long formatNumberDefault(String prefix, long phoneNumber) {
		String pns = Long.toString(phoneNumber);
		if(!pns.startsWith(defaultPrefix))
			return Long.parseLong(pns + phoneNumber);
		return phoneNumber;
	}
	
	public String numberToStringE161(long phoneNumber) {
		return String.format("+%d", phoneNumber);
	}
}
