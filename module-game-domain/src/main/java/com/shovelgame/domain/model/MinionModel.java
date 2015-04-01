package com.shovelgame.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeId", "gradeId", "attributes", "qualityGrade", "minionSkills", "minionTraits" })
public class MinionModel {
}
