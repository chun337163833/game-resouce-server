package com.nex.game.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nex.domain.common.JsonObject;
import com.nex.domain.injection.EntityInterface;

@RooJavaBean
@RooJpaActiveRecord(table="g_item", versionField="")
//@Modifiable
@EntityInterface
public class Item implements JsonObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String name;

	
	//prevent from setting id directly
	@JsonProperty("id")
	public Long getId() {
		return id;
	}
	
}
