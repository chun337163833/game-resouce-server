package org.shovelgame.domain;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.shovelgame.domain.annotation.Publicable;
import org.shovelgame.domain.modification.Modifiable;
import org.shovelgame.domain.modification.ModificationInfoListener;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(persistenceUnit = "puMainDB")
@Publicable
@Modifiable
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=50)
	private String name;
	
	@Lob
	private byte[] image;
}
