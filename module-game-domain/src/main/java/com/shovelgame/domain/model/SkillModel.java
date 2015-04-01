package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.SkillAlgorithm;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "skill_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "skillModelDescirptions", "enchantmentTypes", "heroSkills", "minionSkills", "attributeType" })
public class SkillModel {

    @Column(name = "alg", length = 50)
    @Enumerated
    private SkillAlgorithm alg;
}
