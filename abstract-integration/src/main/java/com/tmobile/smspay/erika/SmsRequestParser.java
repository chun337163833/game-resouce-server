package com.tmobile.smspay.erika;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.tmobile.annotation.Logger;
import com.tmobile.smspay.AbstractSmsRequestParser;
import com.tmobile.smspay.ConfirmRequest;
import com.tmobile.smspay.InitRequest;

@Logger
public class SmsRequestParser implements AbstractSmsRequestParser {
	private static final String ERIKA_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
	
	public Date parseDate(String d) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(ERIKA_DATE_FORMAT);
	    return sdf.parse(d);
	}
	
	/**
	 * Parses sms payment request
	 * 
	 * @param count
	 * @param keyword
	 * @param data
	 * @return
	 */
	public List<InitRequest> parseInitRequest(int count, String keyword, String data) {
		ArrayList<InitRequest> rv = new  ArrayList<InitRequest>();
		
		List<String> rqs = Arrays.asList(data.split("\\$")).stream().filter((i) -> !StringUtils.isEmpty(i)).collect(Collectors.toList());
		
		//data size differs from count
		if(rqs.size()!=count) {
			log.warn(String.format("smspayment init, data size different from request count=%d keyword=%s data='%s'", 
					count, keyword, data));
		}
		
		for(String r : rqs) {
			InitRequest ir = new InitRequest();
			rv.add(ir);
			
			try {
				String [] fields = r.split("\\|");
				
				if(fields.length==0) {
					ir.setParseError(true);
					log.error(String.format("smspayment init, record length=0 count=%d keyword=%s data='%s'", 
							count, keyword, data));
					continue;
				}
				
				ir.setErikaId(Long.parseLong(fields[0]));
				
				if(fields.length!=6) {
					ir.setParseError(true);
					log.error(String.format("smspayment init, record parse error, record fields count does not match. "
							+ "Request count=%d keyword=%s data='%s'", 
							count, keyword, data));					
					continue;
				}
				
				ir.setOperator(fields[1]);
				ir.setSmsReceptionTime(parseDate(fields[2]));
				ir.setShortCode(Long.parseLong(fields[3]));
				ir.setPhoneNumber(Long.parseLong(fields[4]));
				ir.setSmsText(fields[5]);
				
				if(!ir.getSmsText().split(" ")[0].toLowerCase().equals(keyword.toLowerCase()))
					ir.setParseError(true);
			} catch (NumberFormatException | ParseException nfe) {
				log.error(String.format("smspayment init, record parse error count=%d keyword=%s data='%s'", 
						count, keyword, data), nfe);
				ir.setParseError(true);
			}
		}
		
		return rv;
	}
	
	
	public ConfirmRequest parseConfirmRequest(String id, String date, String status, String text) {
		ConfirmRequest rv = new ConfirmRequest();
		
		try {
			rv.setErikaId(Long.parseLong(id));
			rv.setText(text);
			rv.setPaymentDate(parseDate(date));
			rv.setStatus(Integer.parseInt(status));
		} catch (Exception e) {
			log.error(String.format("smspayment confirm, record parse error id=%s date=%s status=%s text='%s'", 
					id, date, status, text), e);
			rv.setParseError(true);
		}
		
		return rv;
	}
}
