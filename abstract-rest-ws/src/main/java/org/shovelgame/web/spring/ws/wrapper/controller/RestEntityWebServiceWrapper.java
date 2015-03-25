package org.shovelgame.web.spring.ws.wrapper.controller;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.shovelgame.annotation.Logger;
import org.shovelgame.domain.common.Entity;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.web.spring.ws.error.FieldError;
import org.shovelgame.web.spring.ws.error.ValidationErrors;
import org.shovelgame.web.spring.ws.exception.ServerErrorException;
import org.shovelgame.web.spring.ws.wrapper.RooBasedEntityFinderService;
import org.shovelgame.web.spring.ws.wrapper.convertor.WrapperConvertor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Logger
public abstract class RestEntityWebServiceWrapper<E extends Entity, W extends JsonObject> {
	
	private RooBasedEntityFinderService<E> finderService = new RooBasedEntityFinderService<>(getEntityClass());
	
	
	@ModelAttribute("entity")
	public E findEntity(@RequestBody(required=false) String body) {
		String id = getPathVariables().get("id");
		if(id == null && body == null) {
			return null;
		} else if (id == null) {
			return this.updateEntity(createNewEntity(), body);
		} else if(body != null) {
			return this.updateEntity(findEntityById(id), body);
		} else {
			return findEntityById(id);
		}
	}

	private E updateEntity(E entity, String body) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			W wrapper = mapper.readValue(body, getJsonClass());
			getConvertor().updateEntity(entity, wrapper);
			return entity;
		} catch (Exception e) {
			log.error("", e);
			throw new ServerErrorException(e.getMessage());
		}
	}
	public abstract WrapperConvertor<E, W> getConvertor();
	
	protected E createNewEntity() {
		try {
			return (E) getEntityClass().newInstance();
		} catch (Exception e) {
			log.error("Create of new entity failed!", e);
			throw new RuntimeException("Create of new entity failed!", e);
		}
	}

	public E findEntityById(String id) {
		return this.finderService.findById(id);
	}

	public ValidationErrors convertErrorsToJson(Errors errors) {
		ValidationErrors e = new ValidationErrors();
		e.setStatus("validation");
		e.setMessage(getEntityClass().getSimpleName() + " is not valid, see errors for more.");
		this.fillErrors(e, errors.getAllErrors());
		return e;
	}
	
	private void fillErrors(ValidationErrors e,  List<ObjectError> errors) {
		for(ObjectError err: errors) {
			FieldError fe = new FieldError();
			if(err instanceof org.springframework.validation.FieldError) {
				fe.setName(((org.springframework.validation.FieldError)err).getField());
			} else {
				fe.setName(err.getObjectName());
			}
			fe.setMessage(err.getCode());
			e.getErrors().add(fe);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		return (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@SuppressWarnings("unchecked")
	public Class<W> getJsonClass() {
		return (Class<W>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}
	public static Map<String, String> getPathVariables() {
		RequestAttributes requestAttributes = RequestContextHolder
				.getRequestAttributes();
		@SuppressWarnings("unchecked")
		Map<String, String> uriTemplateVariables = (Map<String, String>) requestAttributes
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
						RequestAttributes.SCOPE_REQUEST);
		return uriTemplateVariables;
	}

}
