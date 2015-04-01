package com.shovelgame.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = MinionTraitPK.class, versionField = "", table = "minion_trait", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionModel", "traitModel" })
public class MinionTrait {
}
