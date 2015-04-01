package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "trait", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "traitDescriptions", "heroTraits", "minionTraits", "traitTargets", "affectedAttributeType" })
public class Trait {
}
