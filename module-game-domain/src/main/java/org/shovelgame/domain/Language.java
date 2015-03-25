package org.shovelgame.domain;

import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
//@RooJpaActiveRecord(persistenceUnit = "puMainDB", table = "language", versionField="")
//@Publicable
//@EntityInterface
public class Language implements org.shovelgame.domain.localizedfield.Language {
	
	@Id
	private String id;
	private String name;
	
	
//	public static List<Language> findByPublished(Boolean published) {
//        TypedQuery<Language> query = entityManager().createQuery("SELECT o FROM Language o where o.published=:published", Language.class);
//        query.setParameter("published", published);
//        return query.getResultList();
//    }
}
