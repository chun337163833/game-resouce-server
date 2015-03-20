package com.nex.wrapper.conversion.wrapper;

import org.junit.Assert;

import com.nex.web.spring.ws.wrapper.convertor.annotation.AfterEntity;
import com.nex.web.spring.ws.wrapper.convertor.annotation.AfterWrapper;
import com.nex.web.spring.ws.wrapper.convertor.annotation.BeforeEntity;
import com.nex.web.spring.ws.wrapper.convertor.annotation.BeforeWrapper;
import com.nex.web.spring.ws.wrapper.convertor.annotation.WrapperProperty;
import com.nex.web.spring.ws.wrapper.convertor.annotation.WrapperRelation;
import com.nex.wrapper.conversion.entity.EntityA;
import com.nex.wrapper.conversion.entity.NestedEntityFinder;

public class WrapperA {

	private String name;

	@WrapperRelation(service = NestedEntityFinder.class, allowNestedInjection = true)
	private NestedWrapper entity;

	@WrapperRelation(service = NestedEntityFinder.class)
	private NestedWrapper entity2;

	@WrapperProperty(value = "id")
	@WrapperRelation(service = NestedEntityFinder.class)
	private Long entity3;

	
	@BeforeWrapper
	public void beforeWrapper() {
	}
	
	@AfterWrapper
	public void afterWrapper() {
	}
	
	@BeforeEntity
	public void beforeEntity() {
	}
	
	@AfterEntity
	public void afterEntity(EntityA entity) {
		Assert.assertNotNull(entity);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NestedWrapper getEntity() {
		return entity;
	}

	public void setEntity(NestedWrapper entity) {
		this.entity = entity;
	}

	public NestedWrapper getEntity2() {
		return entity2;
	}

	public void setEntity2(NestedWrapper entity2) {
		this.entity2 = entity2;
	}

	public Long getEntity3() {
		return entity3;
	}

	public void setEntity3(Long entity3) {
		this.entity3 = entity3;
	}

}
