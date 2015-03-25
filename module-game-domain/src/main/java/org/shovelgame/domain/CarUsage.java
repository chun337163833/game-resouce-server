package org.shovelgame.domain;

import java.math.BigDecimal;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
@Modifiable
public class CarUsage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	private Driver driver;
	
	@ManyToOne
	@NotNull
	private Car car;
	
	@NotNull
	private BigDecimal initialSurcharge;
	
	@NotNull
	private Integer initialTaxameter;
	
	@NotNull
	private Integer initialTachometer;
	
	@Size(max=255)
	private String initialState;
	
	private BigDecimal endSurcharge;
	
	private Integer endTaxameter;
	
	private Integer endTachometer;
	
	private CarUsageState usageState;
}
