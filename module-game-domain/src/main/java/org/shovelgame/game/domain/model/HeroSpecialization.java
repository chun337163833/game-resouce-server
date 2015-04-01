package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumaration.HeroSpecializationType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "hero_specialization", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "heroTypeDescriptions", "heroModels" })
public class HeroSpecialization {

    @Column(name = "type", length = 50, unique = true)
    @Enumerated
    @NotNull
    private HeroSpecializationType type;
}
