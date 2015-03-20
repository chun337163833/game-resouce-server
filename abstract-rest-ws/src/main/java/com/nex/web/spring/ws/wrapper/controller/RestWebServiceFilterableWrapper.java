package com.nex.web.spring.ws.wrapper.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;

import com.nex.domain.common.Entity;
import com.nex.domain.common.JsonObject;

import cz.tsystems.common.data.filter.Filter;
import cz.tsystems.common.data.filter.FilterUtil;
import cz.tsystems.common.data.filter.FilteredList;
import cz.tsystems.common.data.filter.PageSetting;
import cz.tsystems.common.data.filter.RequestBasedFilter;
import cz.tsystems.common.data.filter.Sort;
import cz.tsystems.common.data.filter.SortDirection;

//@Logger
public abstract class RestWebServiceFilterableWrapper<E extends Entity, W extends JsonObject> extends RestEntityWebServiceWrapper<E, W> {

	private String defaultSortProperty = "id";
	private SortDirection defaultSortDirection = SortDirection.ASC;

	protected void configureFilter(cz.tsystems.common.data.filter.Filter filter, HttpServletRequest request) {

	}

	protected RequestBasedFilter createFilter(HttpServletRequest request) {
		request.setAttribute("filterId", "_null_");
		Map<String, String> defaultConditions = getDefaultConditions(request);
		Sort sort = new Sort(defaultSortProperty, defaultSortDirection);
		RequestBasedFilter filter = new RequestBasedFilter(defaultConditions, sort, new PageSetting(10, true));
		return filter;
	}

	protected Map<String, String> getDefaultConditions(HttpServletRequest request) {
		return Collections.emptyMap();
	}

	@SuppressWarnings("unchecked")
	protected FilteredList<E> getListFilter(FilterUtil jpqlf, Filter filter) {
		try {
			return jpqlf.findByFilter(getEntityClass(), filter);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("", e);
		}
		return new FilteredList<E>(filter, (List<E>) Collections.emptyList(), 0);
	}

}
