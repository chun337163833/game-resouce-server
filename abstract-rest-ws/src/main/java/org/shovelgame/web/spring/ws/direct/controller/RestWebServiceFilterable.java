package org.shovelgame.web.spring.ws.direct.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.shovelgame.common.data.filter.Filter;
import org.shovelgame.common.data.filter.FilterUtil;
import org.shovelgame.common.data.filter.FilteredList;
import org.shovelgame.common.data.filter.PageSetting;
import org.shovelgame.common.data.filter.RequestBasedFilter;
import org.shovelgame.common.data.filter.Sort;
import org.shovelgame.common.data.filter.SortDirection;
import org.shovelgame.domain.common.JsonObject;
import org.slf4j.LoggerFactory;

//@Logger
public abstract class RestWebServiceFilterable<T extends JsonObject> extends RestEntityWebService<T> {

	private String defaultSortProperty = "id";
	private SortDirection defaultSortDirection = SortDirection.ASC;

	protected void configureFilter(Filter filter, HttpServletRequest request) {

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
