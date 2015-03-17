package com.tmobile.domain.smspayment;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

/**
 * Represents one SMS payment message
 * 
 * @author PsencikO
 *
 */
@RooJavaBean
@RooJpaActiveRecord(table="sms_payment", finders={"findSmsPaymentsByErikaId"})
@Table(indexes= {
		@Index(columnList="erikaId", name = "ix_sms_payment_eid"),
		@Index(columnList="phoneNumber,paymentStatus,paymentTime", name = "ix_sms_payment_phone")
})
@NamedQuery(
		name = "findSmsPaymentNotRejected", 
		query = "select p from SmsPayment p where phoneNumber = :phone and (paymentStatus=0 or (paymentStatus=1 and paymentTime > :paymentTime))"
)
public class SmsPayment {
	public static final int SMS_TEXT_MAX_LEN = 160;
	public static final int SMS_ERROR_MAX_LEN = 255;
	public static final int OPERATOR_MAX_LEN = 20;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long erikaId;
	
	@NotNull
	private Long phoneNumber;
	
	/**
	 * Text written by customer in SMS message
	 */
	@NotNull
	@Size(max=160)
	private String smsText;
	
	/**
	 * Time SMS center received payment SMS
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar smsReceptionTime;	
	
	/**
	 * Confirmed time of sms payment, null if payment has not been confirmed yet 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar paymentTime;
	
	/**
	 * Payment status from sms gateway, 0 = transaction not completed, 1 = completed ok, < 0 = completed not ok
	 */
	@NotNull
	private Integer paymentStatus;
	
	/**
	 * Error text sent by Erika, e.g. insufficient credit
	 */
	@Size(max=255)
	private String paymentError;
	
	@Size(max=20)
	private String operator;
	
	private Long shortCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdOn;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedOn;		
}
