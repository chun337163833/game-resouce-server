package com.nex.web.spring.ws.wrapper.controller;

import com.nex.domain.common.Entity;
import com.nex.domain.common.JsonObject;
import com.nex.web.spring.ws.wrapper.convertor.AnnotationMappingConvertor;
import com.nex.web.spring.ws.wrapper.convertor.WrapperConvertor;

public class AnnotationMappingWebServiceWrapper<E extends Entity, W extends JsonObject>
		extends RestWebServiceWrapper<E, W> {

	@Override
	public WrapperConvertor<E, W> getConvertor() {
		return new AnnotationMappingConvertor<>(getEntityClass(), getJsonClass());
	}

}
