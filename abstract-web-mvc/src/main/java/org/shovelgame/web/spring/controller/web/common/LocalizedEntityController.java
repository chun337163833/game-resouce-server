package org.shovelgame.web.spring.controller.web.common;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.shovelgame.common.data.filter.Association;
import org.shovelgame.common.data.filter.Filter;
import org.shovelgame.common.data.filter.FilterUtil;
import org.shovelgame.common.data.filter.FilteredList;
import org.shovelgame.common.data.filter.IFilter;
import org.shovelgame.common.data.filter.QueryManipulator;
import org.shovelgame.common.data.filter.RequestBasedFilter;
import org.shovelgame.common.data.filter.Sort;
import org.shovelgame.common.data.filter.SortDirection;
import org.shovelgame.common.data.filter.querygenerator.QueryGeneratorResult;
import org.shovelgame.domain.localizedfield.LocalizedEntity;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.utils.ReflectionUtils.ArrayMetadata;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class LocalizedEntityController<E extends LocalizedEntity>
		extends DialsController<E> {

	private String datagriMessageKey;
	private String formMessageKey;

	private String includeTemplate;

//	@ModelAttribute("_languages")
//	public List<Language> loadLanguages() {
//		return Language.findByPublished(Boolean.TRUE);
//	}

	@Override
	public void init() {
		setEntityClass(getImplementationClass());
		setControllerURl("localizeddials/");
		setDefaultSortProperty("localizedTexts.value");
		setDefaultSortDirection(SortDirection.ASC);
		setRedirectSaveToList(Boolean.TRUE);
		this.formMessageKey = LocalizedEntity.class.getSimpleName() + ".form";
		this.datagriMessageKey = LocalizedEntity.class.getSimpleName()
				+ ".list";
		this.includeTemplate = includeTemplate();
	}

	public String includeTemplate() {
		return null;
	}

	// @ModelAttribute("_allLanguagesCodes")
	// public String getAllLanguagesCodes() {
	// return StringUtils.createStringFromArray(loadLanguages(), ",", new
	// TypeConversion<Language>() {
	// @Override
	// public String convert(Language str) {
	// return str.getId();
	// }
	// });
	// }

	@Override
	protected FilteredList<E> getListFilter(FilterUtil jpqlf, Filter filter) {
		try {
			return jpqlf.findByFilter(getEntityClass(), filter, new QueryManipulator() {
				@Override
				public QueryGeneratorResult manipulateQuery(QueryGeneratorResult qgr,
						IFilter filter) {
					String dataQuery = qgr.getSelectDataQuery();
					String wrapedClob = "to_char(substr(localizedTexts.value, 0, 100))";
					dataQuery = dataQuery.replace("localizedTexts.value", wrapedClob);
					qgr.setSelectDataQuery(dataQuery);
					return qgr;
				}
			});
		} catch (Exception e) {
			log.error("", e);
		}
		return new FilteredList<E>(filter, (List<E>) Collections.emptyList(), 0);
	}
	
	@ModelAttribute("_datagridMessageKey")
	public String getDatagridMessageKey() {
		return datagriMessageKey;
	}

	@ModelAttribute("_includeTemplate")
	public String getIncludeTemplate() {
		return includeTemplate;
	}

	@ModelAttribute("_formMessageKey")
	public String getFormMessageKey() {
		return formMessageKey;
	}

	

	@Override
	protected void addIgnoreKeys(RequestBasedFilter filter) {
		super.addIgnoreKeys(filter);
		// filter.addIgnoreKey("languageCode");
		// filter.addIgnoreKey("value");
	}

	@Override
	public void configureFilter(Filter filter, HttpServletRequest request) {
		super.configureFilter(filter, request);
		Sort sortBy = filter.getSortBy();
		String method = request.getParameter("_filterAction");
		if (method != null && method.equals("sort")) {
			filter.getConditions().clear();
		}
		String column = sortBy.getColumn();
		ArrayMetadata meta = ReflectionUtils.getArrayData(column);
		String selectedLanguage = getLocale().getLanguage();
		if(getDefaultSortProperty().equals(column)) {
			request.setAttribute("_selectedLanguage", selectedLanguage);
		} else if (meta.getIsArray()) {
			selectedLanguage = meta.getKey();
			sortBy.setColumn(getDefaultSortProperty());
			request.setAttribute("_selectedLanguage", selectedLanguage);
		}
		filter.addDefaultCondition("languageCode", selectedLanguage);
		filter.addAssociation(new Association("localizedTexts"));
		filter.addConditionReplacement("languageCode",
				"localizedTexts.languageCode|Equal(String)");
		filter.addConditionReplacement("value",
				"localizedTexts.value|LRLike(String)");
		String fallbackBindLanguageColumnSort = "name.asMap["
				+ selectedLanguage + "]";
		request.setAttribute("_fallBackSortByColumn",
				fallbackBindLanguageColumnSort);
	}

}
