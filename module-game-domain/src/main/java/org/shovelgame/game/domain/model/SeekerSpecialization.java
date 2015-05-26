package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "seeker_specialization", schema = "model", sequenceName = "model.seeker_specialization_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "seekerSpecializationDescriptions", "seekerModel" })
public class SeekerSpecialization {

    @Column(name = "type", length = 10)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SeekerSpecializationType type;
}
