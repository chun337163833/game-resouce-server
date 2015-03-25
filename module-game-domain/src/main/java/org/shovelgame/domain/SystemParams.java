package org.shovelgame.domain;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB", versionField="")
public class SystemParams {
	@Id
	private String key;
	
	@Size(max=255)
	private String value;
}
