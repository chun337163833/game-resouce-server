package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumeration.SpecializationType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "specialization", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionSpecializationDescriptions", "minionModels" })
public class Specialization {

    @Column(name = "type", length = 50, unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SpecializationType type;
}