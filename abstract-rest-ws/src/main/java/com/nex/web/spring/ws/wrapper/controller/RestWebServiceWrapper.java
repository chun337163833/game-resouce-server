package com.nex.web.spring.ws.wrapper.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nex.domain.common.Entity;
import com.nex.domain.common.JsonObject;
import com.nex.environment.Environment;
import com.nex.environment.EnvironmentType;
import com.nex.logging.injection.Logger;
import com.nex.web.spring.ws.datatype.FilteredListJson;
import com.nex.web.spring.ws.exception.MethodNotAllowedException;
import com.nex.web.spring.ws.exception.RestWebServiceException;
import com.nex.web.spring.ws.exception.ServerErrorException;

import cz.tsystems.common.data.filter.FilterUtil;
import cz.tsystems.common.data.filter.FilteredList;
import cz.tsystems.common.data.filter.RequestBasedFilter;

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

	@EnvironmentType(value={Environment.TEST, Environment.DEVELOPMENT}, exception=MethodNotAllowedException.class)
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
