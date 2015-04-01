package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumaration.HeroSpecializationType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroTypeDescriptions", "heroModels" })
@RooJpaActiveRecord(versionField = "", table = "hero_specialization", schema = "model", sequenceName = "model.hero_specialization_id_seq")
public class HeroSpecialization {

    @Column(name = "type", length = 50, unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private HeroSpecializationType type;
}
