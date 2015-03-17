package com.tmobile.domain.localizedfield;

import java.util.List;
import java.util.Locale;

import com.tmobile.domain.common.Entity;

public interface LocalizedEntity extends Entity {
	
	String getValue(Locale locale);
	String getValue(String locale);
	
	LocalizedValue getName();
	List<LocalizedField> getLocalizedTexts();
}
