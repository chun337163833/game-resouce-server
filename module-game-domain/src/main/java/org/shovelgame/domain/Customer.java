package org.shovelgame.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB", finders = {"findCustomersByCellPhone"})
public class Customer extends TxUser {
	private static final long serialVersionUID = -159958132687231729L;

	private String title;

	private Address address;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Calendar birthDate;

	private Boolean blackListed;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd.MM.yyyy HH:mm:ss")
	private Calendar basicDiscountPaidDate;

	@ManyToOne
	private Tariff tariff;

	@ManyToOne
	private Company company;

	@OneToMany(mappedBy = "customer")
	private List<Rating> ratings;
	
	public boolean isCustomerBlacklisted() {
		return getBlackListed()!=null && getBlackListed().booleanValue()==true;
	}
	
	public boolean updateDiscountPaidDate(Calendar discountPaidDate) {
		if(getBasicDiscountPaidDate()==null || 
				(discountPaidDate!=null && getBasicDiscountPaidDate().before(discountPaidDate))) {
			setBasicDiscountPaidDate(discountPaidDate);
			return true;
		}
		
		return false;
	}
}
