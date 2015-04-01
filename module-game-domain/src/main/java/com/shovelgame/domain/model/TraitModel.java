package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.SkillAlgorithm;
import com.shovelgame.enumaration.TraitAlgorithm;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "trait_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "traitModelDescriptions", "heroTraits", "minionTraits", "attributeType" })
public class TraitModel {

    @Column(name = "alg", length = 50)
    @Enumerated
    private TraitAlgorithm alg;

    @Column(name = "affected_skill_alg", length = 50)
    private SkillAlgorithm affectedSkillAlg;
}
