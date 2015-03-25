package org.shovelgame.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
public class Driver extends TxUser {
	private static final long serialVersionUID = -655958432687234729L;
	
	private Address address;
	
	private Long homePhone;
	
	@Lob
	private String note;
}
