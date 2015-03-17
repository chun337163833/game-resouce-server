package com.tmobile.web.spring.controller.ws.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;

import com.tmobile.domain.common.JsonResponse;

import cz.tsystems.common.data.filter.Filter;
import cz.tsystems.common.data.filter.FilterUtil;
import cz.tsystems.common.data.filter.FilteredList;
import cz.tsystems.common.data.filter.PageSetting;
import cz.tsystems.common.data.filter.RequestBasedFilter;
import cz.tsystems.common.data.filter.Sort;
import cz.tsystems.common.data.filter.SortDirection;

//@Logger
public abstract class RestWebServiceFilterable<T extends JsonResponse> extends RestWebEntityService<T> {

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
	protected FilteredList<T> getListFilter(FilterUtil jpqlf, Filter filter) {
		try {
			return jpqlf.findByFilter(getEntityClass(), filter);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("", e);
		}
		return new FilteredList<T>(filter, (List<T>) Collections.emptyList(), 0);
	}

}
