package org.shovelgame.web.spring.ws.wrapper.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.shovelgame.annotation.Logger;
import org.shovelgame.common.data.filter.FilterUtil;
import org.shovelgame.common.data.filter.FilteredList;
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
public abstract class RestWebServiceWrapper<E extends Entity, W extends JsonObject> extends RestWebServiceFilterableWrapper<E, W> {
	
	@Resource(name = "jpqlfilter")
	private FilterUtil jpqlFilter;

	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public FilteredListJson list(HttpServletRequest request, HttpServletResponse response) {
		RequestBasedFilter filter = createFilter(request);
		filter.addIgnoreKey("lang");
		filter.addIgnoreKey("ajax");
		filter.setKeyForEnablePagigng("paginable");
		filter.setKeyForPageSize("pageSize");
		filter.configureFilterFromHttp(request, response);
		configureFilter(filter, request);
		FilteredList<E> flist = getListFilter(this.jpqlFilter, filter);
		return new FilteredListJson(flist, getConvertor().convertListOfWrappers(flist.getData()));
	}
	
	@RequestMapping(value={"/"}, method = RequestMethod.POST, headers = { "content-type=application/json" })
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = { RestWebServiceException.class, ServerErrorException.class })
	public W _create(@ModelAttribute("entity") @Valid E data, Errors errors) {
		if (errors.hasErrors()) {
			throw new RestWebServiceException(HttpStatus.UNPROCESSABLE_ENTITY, this.convertErrorsToJson(errors));
		}
		data.persist();
		data.flush();
		return getConvertor().convertToWrapper(data);
	}

	@Environment(value={EnvironmentType.TEST, EnvironmentType.DEVELOPMENT}, exception=MethodNotAllowedException.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional()
	public W _delete(@ModelAttribute("entity") @Valid E data, Errors errors) {
		data.remove();
		return getConvertor().convertToWrapper(data);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = {"content-type=application/json"})
	@Transactional(noRollbackFor = { RestWebServiceException.class, ServerErrorException.class })
	public W _update(@ModelAttribute("entity") @Valid E data, Errors errors) {
		if (errors.hasErrors()) {
			try {
				throw new RestWebServiceException(HttpStatus.UNPROCESSABLE_ENTITY, this.convertErrorsToJson(errors));
			} catch (Exception e) {
				throw new ServerErrorException(e.getMessage());
			}
		}
		return getConvertor().convertToWrapper(data);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public W detail(@ModelAttribute("entity") E data) {
		return getConvertor().convertToWrapper(data);
	}
}
