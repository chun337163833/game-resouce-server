package org.shovelgame.web.freemarker;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.web.spring.controller.web.common.NestingEntityRestfulCRUDController;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.PatternMatchUtils;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Configurable
public class FormFieldModel {

	@Resource(name = "validationResourceMessageBundle")
	private ReloadableResourceBundleMessageSource validationMessageResourceBundle;
	private FormModel formModel;
	private ErrorList fieldErrors;
	private String path;
	private Locale locale;
	public FormFieldModel(String path, String commandName, FormModel model, Locale locale) {
		super(); //NOSONAR
		Errors err = model.getRequestContext().getErrors(commandName);
		if(err != null)
		fieldErrors = new ErrorList(err.getFieldErrors(path)) ;
		if(fieldErrors == null) {
			fieldErrors = new ErrorList();
		}
		this.path = path;
		this.formModel = model;
		this.locale = locale;
	}
	
	public Boolean hasError() {
		return fieldErrors.hasErrors();
	}
	public ErrorList getErrorMessages() {
		return fieldErrors;
	}
	public Boolean isRequired(Boolean check) {
		if(check) {
			Field f = ReflectionUtils.findField(formModel.getFormObject().getClass(), this.path);
			if(f == null) return Boolean.FALSE;
			boolean mainNotEmpty = f.getAnnotation(NotNull.class) != null || f.getAnnotation(NotEmpty.class)!= null;
			
			if(f.getAnnotation(Size.class) != null) {
				mainNotEmpty = mainNotEmpty || f.getAnnotation(Size.class).min()>0;			
			};
			return mainNotEmpty;
		}
		return Boolean.FALSE;
		
	}
	public class ErrorList implements Iterator<TranslatedError> {
		
		private List<FieldError> errors;

		public ErrorList() {
			this.errors = Collections.emptyList();
		}
		
		public ErrorList(List<FieldError> errors) {
			this.errors = errors;
		}
		
		public boolean hasErrors() {
			return this.errors.size() > 0;
		}

		private int index = 0;
		@Override
		public boolean hasNext() {
			return ErrorList.this.errors.size() > index;
		}
		@Override
		public TranslatedError next() {
			TranslatedError error = new TranslatedError(ErrorList.this.errors.get(index++));
			return error;
		}
		@Override
		public void remove() {
			
		}
		
	}
	
	public class TranslatedError {
		private FieldError originalError;
		public TranslatedError(FieldError originalError) {
			super();
			this.originalError = originalError;
		}
		
		public String getMessage() {
			if(validationMessageResourceBundle != null ) {
				for(String code: this.originalError.getCodes()) {
					String _msg = validationMessageResourceBundle.getMessage(code, this.originalError.getArguments(), code, FormFieldModel.this.locale);
					if(!_msg.equals(code)) {
						return _msg;
					}
				}
			}
			return this.originalError.getDefaultMessage();
		}
	}
	
	
	public boolean isDisabled() {
		String[] fields = (String[]) this.formModel.getRequestContext().getModel().get(NestingEntityRestfulCRUDController.DISALLOWED_FIELDS_MODEL);
		for(String field: fields) {
			if(PatternMatchUtils.simpleMatch(field, this.path)) {
				return true;
			}
		}
 		return false;
	}

	
}
