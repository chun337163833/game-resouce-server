package com.tmobile.domain.aspect;

import java.lang.reflect.Field;
import java.util.Locale;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.AnnotationException;

import com.tmobile.domain.localizedfield.Language;
import com.tmobile.domain.localizedfield.LocalizedEntity;
import com.tmobile.domain.localizedfield.LocalizedField;
import com.tmobile.domain.localizedfield.LocalizedFieldAdapter;
import com.tmobile.domain.localizedfield.LocalizedValue;



public aspect LocalizedEntityAspect {
	
	@Transient
	private transient LocalizedFieldAdapter LocalizedEntity._name;

	public LocalizedValue LocalizedEntity.getName() {
		if (_name == null) {
			try {
				Field f = getClass().getDeclaredField("localizedTexts");
				if(f == null) {
					throw new NullPointerException("localizedTexts must be declared in class " + getClass());
				}
				OneToMany otm = f.getAnnotation(OneToMany.class);
				if(otm == null || otm.targetEntity().equals(void.class)) {
					throw new AnnotationException("localizedTexts field in class " + getClass() + " must have annotaton " + OneToMany.class + " and targetEntity must implements " + LocalizedField.class);
				}
				_name = new LocalizedFieldAdapter(otm.targetEntity(), getLocalizedTexts(), "value");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return _name;
	}
	
	public String LocalizedEntity.getValue(Locale locale) {
		return getName().get(locale);
	}
	public String LocalizedEntity.getValue(String locale) {
		return getName().get(locale);
	}
	public String LocalizedEntity.getValue(Language locale) {
		return getName().get(locale);
	}
}
