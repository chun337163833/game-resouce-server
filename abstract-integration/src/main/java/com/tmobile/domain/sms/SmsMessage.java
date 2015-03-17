package com.tmobile.domain.sms;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooToString
@RooJavaBean
@RooJpaActiveRecord(versionField="")
public class SmsMessage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long phoneNumber;
	
	private String message;

	private OutgoingSmsState state = OutgoingSmsState.NEW;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationTime = new GregorianCalendar();
}
