package org.shovelgame.domain.aspect;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlTransient;

import org.shovelgame.domain.annotation.Publicable;




public aspect CommonFieldsAspect {

	
	public interface PublishedFieldDeclaration { Boolean getPublished(); void setPublished(Boolean value);}
	
	@XmlTransient
	@Column(name = "published")
	private Boolean PublishedFieldDeclaration.published = Boolean.FALSE;

	public Boolean PublishedFieldDeclaration.getPublished() {
		return this.published;
	}
	
	public void PublishedFieldDeclaration.setPublished(Boolean published) {
		this.published = published;
	}
	
	declare parents: @ExperienceSetter * implements PublishedFieldDeclaration;
}
