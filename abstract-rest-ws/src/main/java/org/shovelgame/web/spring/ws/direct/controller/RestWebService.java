package org.shovelgame.web.spring.ws.direct.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.shovelgame.annotation.Logger;
import org.shovelgame.common.data.filter.FilterUtil;
import org.shovelgame.common.data.filter.RequestBasedFilter;
import org.shovelgame.domain.common.Entity;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.environment.EnvironmentType;
import org.shovelgame.environment.Environment;
import org.shovelgame.web.spring.ws.datatype.FilteredListJson;
import org.shovelgame.web.spring.ws.exception.MethodNotAllowedException;
import org.shovelgame.web.spring.ws.exception.RestWebServiceException;
import org.shovelgame.web.spring.ws.exception.ServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Logger
public abstract class RestWebService<T extends JsonObject> extends RestWebServiceFilterable<T> {
	
	@Resource(name = "jpqlfilter")
	private FilterUtil jpqlFilter;

	@RequestMapping(value={"/", ""}, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public FilteredListJson list(HttpServletRequest request, HttpServletResponse response) {
		RequestBasedFilter filter = createFilter(request);
		filter.addIgnoreKey("lang");
		filter.addIgnoreKey("ajax");
		filter.setKeyForEnablePagigng("paginable");
		filter.setKeyForPageSize("pageSize");
		// filter.configureFromDatabase(request);
		filter.configureFilterFromHttp(request, response);
		configureFilter(filter, request);
		return new FilteredListJson(getListFilter(this.jpqlFilter, filter));
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST, headers = { "content-type=application/json" })
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = { RestWebServiceException.class, ServerErrorException.class })
	public T _create(@ModelAttribute("entity") @Valid T data, Errors errors) {
		if (errors.hasErrors()) {
//			try {
				throw new RestWebServiceException(HttpStatus.UNPROCESSABLE_ENTITY, this.convertErrorsToJson(errors));
//			} catch (Exception e) {
//				throw new ServerErrorException(e.getMessage());
//			}
		}
		if (data instanceof Entity) {
			Entity entity = (Entity) data;
			entity.persist();
			entity.flush();
		}
		return data;
	}

	@Environment(value={EnvironmentType.TEST, EnvironmentType.DEVELOPMENT}, exception=MethodNotAllowedException.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional()
	public T _delete(@ModelAttribute("entity") @Valid T data, Errors errors) {
		if (data instanceof Entity) {
			Entity entity = (Entity) data;
			entity.remove();
		}
		return data;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = {"content-type=application/json"})
	@Transactional(noRollbackFor = { RestWebServiceException.class, ServerErrorException.class })
	public T _update(@ModelAttribute("entity") @Valid T entity, Errors errors) {
		if (errors.hasErrors()) {
			try {
				throw new RestWebServiceException(HttpStatus.UNPROCESSABLE_ENTITY, this.convertErrorsToJson(errors));
			} catch (Exception e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return entity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public T detail(@ModelAttribute("entity") T entity) {
		return entity;
	}
}
