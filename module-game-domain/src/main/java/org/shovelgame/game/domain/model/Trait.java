package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumaration.SkillAlgorithm;
import org.shovelgame.game.domain.enumaration.TraitAlgorithm;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "traitDescriptions", "heroTraits", "minionTraits", "traitTargets", "affectedAttributeType" })
@RooJpaActiveRecord(versionField = "", table = "trait", schema = "model", sequenceName = "model.trait_id_seq")
public class Trait {

    @Column(name = "affected_skill_alg", length = 50)
    @Enumerated(EnumType.STRING)
    private SkillAlgorithm affectedSkillAlg;

    @Column(name = "alg", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TraitAlgorithm alg;
}
