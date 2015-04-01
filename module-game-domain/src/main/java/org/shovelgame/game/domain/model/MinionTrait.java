package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionModel", "trait" })
@RooJpaActiveRecord(identifierType = MinionTraitPK.class, versionField = "", table = "minion_trait", schema = "model", sequenceName = "")
public class MinionTrait {
}
