package org.shovelgame.domain;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
@Modifiable
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=50)
	private String companyName;
	
	@Size(max=70)
	private String companyRepresentative;
	
	private Address address;

	@Size(max=50)
	private String country;
	
	@Size(max=30)
	private String phone;
	
	@Size(max=50)
	private String email;
	
	@Size(max=10)
	private String ic;
	
	@Size(max=15)
	private String dic;
}
