package org.shovelgame.web.spring.controller.web.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.shovelgame.common.data.filter.Filter;
import org.shovelgame.common.data.filter.FilterUtil;
import org.shovelgame.common.data.filter.FilteredList;
import org.shovelgame.common.data.filter.PageSetting;
import org.shovelgame.common.data.filter.RequestBasedFilter;
import org.shovelgame.common.data.filter.Sort;
import org.shovelgame.common.data.filter.SortDirection;
import org.shovelgame.domain.common.Entity;
import org.shovelgame.utils.Requestutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

public abstract class NestingEntityRestfulCRUDController<T extends Entity> extends NestingRestfulCRUDController<T> {

	static final Logger log = LoggerFactory
			.getLogger(NestingEntityRestfulCRUDController.class);

	@Resource(name = "jpqlfilter")
	private FilterUtil jpqlFilter;
	
	public static final String DISALLOWED_FIELDS_MODEL = "_disallowedFields";
	
	private String controllerURl;
	protected String redirectUrl = "redirect:";
	protected String templateName = "edit";
	private String defaultSortProperty = "id";
	private SortDirection defaultSortDirection = SortDirection.ASC;
	private int filterType;
	protected boolean redirectSaveToList = false;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields(getFullDisallowedFields());
	}
	@ModelAttribute(DISALLOWED_FIELDS_MODEL)
	public String[] loadDisallowedFieldsToRequest() {
		return this.getFullDisallowedFields();
	}
		
	private String[] getFullDisallowedFields() {
		String[] dis = getDisallowedFields();
		List<String> list = new ArrayList<String>(Arrays.asList(dis));
		list.add("id");
		return list.toArray(new String[list.size()]);
	}
		
	
	public String[] getDisallowedFields() {
		return new String[0];
	}
	
	public NestingEntityRestfulCRUDController() {
		init();
	}

	public abstract void init();

	/**
	 * Metoda, ktera se zavola, pokud se bude vytvaret entita. Pred zavolanim
	 * persist.
	 * 
	 * @param entity
	 */
	protected void onEntityCreated(T entity) {
	}

	/**
	 * Metoda se zavola pri zmene entity pred zavolanim flush.
	 * 
	 * @param newEntity
	 * @param oldEntity
	 */
	protected void onEntityChanged(T newEntity, T oldEntity) {
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String _create(@ModelAttribute("entity") @Valid T entity,
			Errors errors, Model uiModel, HttpServletRequest request) {
		try {
			additionalValidate(entity, errors);
			if (errors.hasErrors()) {
				// BeanUtils.setProperty(entity, "id", null);
				configureEditDialog(uiModel, request, entity);
				rejectFormErrors(RequestContextUtils.getLocale(request), errors);
				return resolveEditViewName(entity);
			}
			_saveNewEntity(entity, errors, uiModel);
			request.setAttribute("success", true);
			if (redirectSaveToList) {
				return "redirect:";
			} else {
				return controllerRedirectUrl() + entity.getId();
			}
		} catch (Exception e) {

			log.info("", e);
			rejectAndTranslateError(RequestContextUtils.getLocale(request), errors,
					"form.actions.save.error.message.persist",
					new Object[] { e.getMessage() });
			configureEditDialog(uiModel, request, entity);
			return resolveEditViewName(entity);
		}
	}

	protected boolean checkPermission(T entity) {
		return true;
	}

	protected void _saveNewEntity(T entity, Errors errors, Model uiModel) {
		entity.persist();
		entity.flush();
		onEntityCreated(entity);
	}

	protected void additionalValidate(T entity, Errors errors) {
	}

	public void entityUpdateRejected(T entity) {
	}
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String _update(@ModelAttribute("entity") @Valid T entity,
			Errors errors, Model uiModel, HttpServletRequest request) {
		try {
			additionalValidate(entity, errors);
			if (errors.hasErrors()) {
				configureEditDialog(uiModel, request, entity);
				getEntityManager(entity).detach(entity);
				entityUpdateRejected(entity);
				rejectFormErrors(RequestContextUtils.getLocale(request), errors);
				return resolveEditViewName(entity);
			}
			_updateEntity(entity, errors, uiModel, request);
			request.setAttribute("success", true);
			if (redirectSaveToList) {
				return "redirect:";
			} else {
				return controllerRedirectUrl() + entity.getId();
			}
		} catch (Exception e) {
			log.info("", e);
			rejectAndTranslateError(RequestContextUtils.getLocale(request), errors,
					"form.actions.save.error.message.persist",
					new Object[] { e.getMessage() });
			configureEditDialog(uiModel, request, entity);
			return resolveEditViewName(entity);
		}
	}

	/**
	 * Metoda slouží pro konfiguraci seznamu. V případě, že nestačí seznam všech
	 * entit, přepíše se tato methoda. Pozn.: Filtr v tomto případě není zcela
	 * šťastný název, odvozuje se ze slova jpqlfiltru.
	 * 
	 * @param filter
	 *            filtr báze dat
	 * @return jpql filter, což je vlastně seznam entitt
	 */
	@SuppressWarnings("unchecked")
	protected FilteredList<T> getListFilter(FilterUtil jpqlf, Filter filter) {
		try {
			return jpqlf.findByFilter(getEntityClass(), filter);
		} catch (Exception e) {
			log.error("", e);
		}
		return new FilteredList<T>(filter, (List<T>) Collections.emptyList(), 0);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request,
			HttpServletResponse response, Model uiModel) {

		RequestBasedFilter filter = createFilter(request);
		filter.addIgnoreKey("lang");
		filter.addIgnoreKey("ajax");
		addIgnoreKeys(filter);
		// filter.configureFromDatabase(request);
		filter.configureFilterFromHttp(request, response);
		configureFilter(filter, request);

		FilteredList<T> list = getListFilter(this.jpqlFilter, filter);
		uiModel.addAttribute("entities", list);
		uiModel.addAttribute("entities_data", list.getData());
		uiModel.addAttribute("entities_filter", list.getFilter());

		return controllerUrl() + "list";
	}

	protected void addIgnoreKeys(RequestBasedFilter filter) {

	}

	protected RequestBasedFilter createFilter(HttpServletRequest request) {
		request.setAttribute("filterId", "_null_");
		Map<String, String> defaultConditions = getDefaultConditions(request);
		Sort sort = new Sort(defaultSortProperty, defaultSortDirection);
		RequestBasedFilter filter = new RequestBasedFilter(defaultConditions,
				sort, new PageSetting(10, true));
		return filter;
	}

	protected Map<String, String> getDefaultConditions(
			HttpServletRequest request) {
		return Collections.emptyMap();
	}

	public void configureFilter(Filter filter,
			HttpServletRequest request) {

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected T findEntityWithIdInNewTrans(HttpServletRequest request, String id) {
		return findEntityWithId(request, id);
	}

	protected void _updateEntity(T entity, Errors errors, Model uiModel,
			HttpServletRequest request) {
		invokeOnChanged(request, entity);
		entity.flush();
	}

	protected void invokeOnChanged(HttpServletRequest request, T entity) {
		T oldEntity = findEntityWithIdInNewTrans(request, entity.getId()
				.toString());
		onEntityChanged(entity, oldEntity);
	}

//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	public String _delete(@ModelAttribute("entity") T entity, Model uiModel) {
//		try {
//			entity.remove();
//			entity.flush();
//			return controllerRedirectUrl();
//		} catch (Exception e) {
//			log.info("", e);
//			return controllerRedirectUrl() + entity.getId();
//		}
//	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public String _deleteMultiple(@ModelAttribute("entities") List<T> entities, HttpServletRequest request, HttpServletResponse response, Errors errors, Model uiModel) {
		try {
			if (entities != null && !entities.isEmpty())
				for (T entity : entities) {
					try {
						entity.remove();
						entity.flush();
					} catch (Exception e) {
						getEntityManager(entity).detach(entity);
						rejectAndTranslateError(RequestContextUtils.getLocale(request), errors,
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

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String _show(@ModelAttribute("entity") T entity, Model uiModel,
			HttpServletRequest request) {
		configureEditDialog(uiModel, request, entity);
		boolean accessAllowed = checkPermission(entity);
		if (!accessAllowed) {
			return Requestutils.createRedirect(
					"/error/" + HttpStatus.FORBIDDEN, false);
		}
		return resolveEditViewName(entity);
	}

	protected void configureEditDialog(Model uiModel,
			HttpServletRequest request, T entity) {
	}

	protected void _deleteEntity(T entity) {
		entity.remove();
		entity.flush();
	}

	protected EntityManager getEntityManager(T entity) {
		Method method = ReflectionUtils.findMethod(entity.getClass(),
				"entityManager");
		EntityManager em = (EntityManager) ReflectionUtils.invokeMethod(method,
				null);
		return em;
	}

	protected String resolveEditViewName(T entity) {
		return controllerUrl() + getDetailTemplateName();
	}

	public String controllerUrl() {
		return controllerURl;
	}

	public void setControllerURl(String controllerURl) {
		this.controllerURl = controllerURl;
	}

	protected String controllerRedirectUrl() {
		return redirectUrl;
	}

	protected String getDetailTemplateName() {
		return templateName;
	}

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	public boolean isRedirectSaveToList() {
		return redirectSaveToList;
	}

	public void setRedirectSaveToList(boolean redirectSaveToList) {
		this.redirectSaveToList = redirectSaveToList;
	}

	public String getDefaultSortProperty() {
		return defaultSortProperty;
	}

	public void setDefaultSortProperty(String defaultSortProperty) {
		this.defaultSortProperty = defaultSortProperty;
	}

	public SortDirection getDefaultSortDirection() {
		return defaultSortDirection;
	}

	public void setDefaultSortDirection(SortDirection defaultSortDirection) {
		this.defaultSortDirection = defaultSortDirection;
	}
	
	public Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}
	
}
