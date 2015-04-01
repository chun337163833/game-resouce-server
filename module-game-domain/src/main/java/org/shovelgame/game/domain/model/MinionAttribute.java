package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "type", "minionModel" })
@RooJpaActiveRecord(versionField = "", table = "minion_attribute", schema = "model", sequenceName = "model.minion_attribute_id_seq")
public class MinionAttribute {
}
