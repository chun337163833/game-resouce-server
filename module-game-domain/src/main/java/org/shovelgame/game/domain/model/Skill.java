package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "skill", schema = "model", sequenceName = "model.skill_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "skillDescriptions", "skillNames", "enchantmentTypes", "minionSkills", "attributeType" })
public class Skill {

    @Column(name = "alg", length = 50)
    @NotNull
    @Enumerated
    private SkillAlgorithm alg;
}
