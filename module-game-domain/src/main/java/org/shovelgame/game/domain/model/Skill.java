package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "skillDescriptions", "enchantmentTypes", "heroSkills", "minionSkills", "attributeType" })
@RooJpaActiveRecord(versionField = "", table = "skill", schema = "model", sequenceName = "model.skill_id_seq")
public class Skill {
}
