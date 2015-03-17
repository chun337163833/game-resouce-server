package com.tmobile.smspay;

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class ConfirmRequest {
	private Long erikaId;
	
	private Date paymentDate;
	
	private int status;
	
	private String text;
	
	private boolean parseError = false;
}
