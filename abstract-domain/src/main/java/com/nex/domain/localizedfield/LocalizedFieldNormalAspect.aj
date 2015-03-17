package com.nex.domain.localizedfield;

import javax.persistence.Column;

import org.hibernate.annotations.NaturalId;

public aspect LocalizedFieldNormalAspect {
	
	
	@Column(name = "lang")
	@NaturalId(mutable = true)
	private String LocalizedFieldNormal.languageCode;
	
	@NaturalId(mutable = true)
	@Column(name="value")
	private String LocalizedFieldNormal.value;
	
	
	public String LocalizedFieldNormal.getLanguageCode() {
		return this.languageCode;
	}
	
	
	public String LocalizedFieldNormal.getValue() {
		return this.value;
	}
	public void LocalizedFieldNormal.setLanguageCode(
			String languageCode) {
		this.languageCode = languageCode;
	}
	public void LocalizedFieldNormal.setValue(String value) {
		this.value = value;
	}
}
