package org.shovelgame.game.domain.model;
import org.shovelgame.game.domain.Attribute;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "type", "heroModel" })
@RooJpaActiveRecord(versionField = "", table = "hero_attribute", schema = "model", sequenceName = "model.hero_attribute_id_seq")
public class HeroAttribute implements Attribute {
}
