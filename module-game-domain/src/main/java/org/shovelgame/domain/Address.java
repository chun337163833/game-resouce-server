package org.shovelgame.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;

@Embeddable
@RooJavaBean
public class Address {
	@Size(max=255)
	private String street;
	
	@Size(max=50)
	private String city;
	
	@Size(max=20)
	private String zip;
}
