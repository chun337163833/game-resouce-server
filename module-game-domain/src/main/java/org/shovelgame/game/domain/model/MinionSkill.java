package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = MinionSkillPK.class, versionField = "", table = "minion_skill", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionModel", "skill" })
public class MinionSkill {
}
