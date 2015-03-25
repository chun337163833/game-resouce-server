package org.shovelgame.domain;

import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.annotation.Publicable;
import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@Publicable
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
@Modifiable
@EntityInterface
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Size(max=50)
	private String manufacturer;
	
	@Size(max=50)
	private String model;
	
	@NotNull
	@Size(max=10)
	private String licensePlate;
	
	private Integer capacity;
	
	private Integer yearOfProduction;
	
	@Size(max=30)
	private String color;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="car")
	private List<CarLog> carLogs;
	
	/**
	 * Number of taxi which is visible on the car - taxi will be identified by this number
	 * not license plate
	 */
	private Integer carNumber;	
}
