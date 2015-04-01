package com.shovelgame.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = HeroSkillPK.class, versionField = "", table = "hero_skill", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroModel", "skillModel" })
public class HeroSkill {
}
