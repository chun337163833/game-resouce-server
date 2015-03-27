package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.SpecializationType;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "seeker_specialization", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "specializationDescs", "seekerId", "seekerModel" })
public class SeekerSpecialization {

    @Column(name = "type", length = 10)
    @NotNull
    @Enumerated
    private SpecializationType type;
}
