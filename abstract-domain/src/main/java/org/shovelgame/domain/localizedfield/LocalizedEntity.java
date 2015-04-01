package org.shovelgame.domain.localizedfield;

import java.util.List;
import java.util.Locale;

import org.shovelgame.domain.common.Entity;

public interface LocalizedEntity extends Entity {
	
	String getValue(Locale locale);
	String getValue(String locale);
	
	LocalizedValue getName();
	List<LocalizedField> getLocalizedTexts();
}
