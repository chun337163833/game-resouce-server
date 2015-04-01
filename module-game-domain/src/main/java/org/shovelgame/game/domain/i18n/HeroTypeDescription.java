package org.shovelgame.game.domain.i18n;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "lang", "heroType" })
@RooJpaActiveRecord(versionField = "", table = "hero_type_description", schema = "i18n", sequenceName = "i18n.hero_type_description_id_seq")
public class HeroTypeDescription {
}
