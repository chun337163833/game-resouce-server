package org.shovelgame.i18n;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "hero_type_description", schema = "i18n")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "lang" })
public class HeroTypeDescription {
}
