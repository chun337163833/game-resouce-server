package com.tmobile.smspay;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class InitRequest {
	private Long erikaId;
	private String operator;
	private Date smsReceptionTime;
	private long shortCode;
	private long phoneNumber;
	private String smsText;
	
	private boolean parseError = false;
}
