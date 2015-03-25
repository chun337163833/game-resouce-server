package org.shovelgame.web.spring.ws.wrapper.controller;

import org.shovelgame.domain.common.Entity;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.web.spring.ws.wrapper.convertor.AnnotationMappingConvertor;
import org.shovelgame.web.spring.ws.wrapper.convertor.WrapperConvertor;

public class AnnotationMappingWebServiceWrapper<E extends Entity, W extends JsonObject>
		extends RestWebServiceWrapper<E, W> {

	@Override
	public WrapperConvertor<E, W> getConvertor() {
		return new AnnotationMappingConvertor<>(getEntityClass(), getJsonClass());
	}

}
