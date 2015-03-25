package org.shovelgame.web.spring.ws.direct.controller;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shovelgame.annotation.Logger;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.web.spring.ws.error.FieldError;
import org.shovelgame.web.spring.ws.error.ValidationErrors;
import org.shovelgame.web.spring.ws.exception.ServerErrorException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Logger
public class RestEntityWebService<T extends JsonObject> {

	@ModelAttribute("entity")
	public T findEntity(@RequestBody(required=false) String body) {
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

	protected T updateEntity(T entity, String body) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readerForUpdating(entity).readValue(body);
		} catch (Exception e) {
			log.error("", e);
			throw new ServerErrorException(e.getMessage());
		}
	}
	
	protected T createNewEntity() {
		try {
			return (T) getEntityClass().newInstance();
		} catch (Exception e) {
			log.error("Create of new entity failed!", e);
			throw new RuntimeException("Create of new entity failed!", e);
		}
	}

	protected T findEntityWithId(HttpServletRequest request, String id) {
		return findEntityById(id);
	}

	public T findEntityById(String id) {
		Class<T> entityClass = getEntityClass();
		String findByIdMethodName = "find" + entityClass.getSimpleName();
		Method findByIdMethod = ReflectionUtils.findMethod(entityClass,
				findByIdMethodName, Long.class);
		@SuppressWarnings("unchecked")
		T entity = (T) ReflectionUtils.invokeMethod(findByIdMethod, null, Long.valueOf(id));
		if (entity == null) {
			throw new ServerErrorException("Entity "
					+ entityClass.getSimpleName() + " with id=" + id
					+ " not found.");
		}
		return entity;
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
	public Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
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
