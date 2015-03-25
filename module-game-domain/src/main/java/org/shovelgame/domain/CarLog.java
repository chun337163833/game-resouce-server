package org.shovelgame.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
@Modifiable
@EntityInterface
public class CarLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Temporal(TemporalType.DATE)
	private Calendar logDate;
	
	@Size(max=255)
	private String logText;
	
	@NotNull
	private int tachometer;
	
	@Size(max=50)
	private String serviceName;
	
	private BigDecimal price;
	
	@ManyToOne
	private Car car;
}
