package com.nex.web.spring.controller.web.common;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nex.domain.aspect.CommonFieldsAspect.PublishedFieldDeclaration;
import com.nex.domain.common.Entity;

public abstract class DialsController<T extends Entity> extends NestingEntityRestfulCRUDController<T> {

	
	
	@ModelAttribute("_implClass")
	public String getImplClass() {
		return getImplementationClass().getSimpleName();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericType() {
		ParameterizedType parameterizedType = (ParameterizedType)getClass()
                .getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@ModelAttribute("_implementationClass")
	protected Class<T> getImplementationClass() {
		return getGenericType();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String _multipleAction(@ModelAttribute("entities") List<T> entities, @RequestParam(value="_option", required=true) Boolean state,  HttpServletRequest request, HttpServletResponse response, Errors errors, Model uiModel) {
		try {
			if (entities != null && !entities.isEmpty())
				for (T entity : entities) {
					try {
						if(entity instanceof PublishedFieldDeclaration) {
//							((PublishedFieldDeclaration)entity).markAsPublished();
							((PublishedFieldDeclaration)entity).setPublished(state);
						}
						entity.flush();
					} catch (Exception e) {
						log.error("", e);
						getEntityManager(entity).detach(entity);
						rejectAndTranslateError(getLocale(), errors,
								"form.actions.delete.error.message.persist",
								new Object[] { e });
					}
				}
			if (errors.hasErrors()) {
				return list(request, response, uiModel);
			}
			return controllerRedirectUrl();
		} catch (Exception e) {
			log.info("", e);
			return controllerRedirectUrl();
		}
	}
	
	@ModelAttribute("_isDial")
	public Boolean isDial() {
		return Boolean.TRUE;
	}
	
}
