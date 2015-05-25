package org.shovelgame.game.domain.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
//@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "seeker_model", schema = "model", sequenceName = "model.seeker_model_id_seq")
@RooToString(excludeFields = { "seekerSpecializations", "qualityGrade", "missionRewards" })
public class SeekerModel {
	
	@OneToMany(mappedBy = "seekerModel")
    private Set<SeekerSpecialization> seekerSpecializations;
    
    @Column(name = "name", length = 50)
    @NotNull
    private String name;
    
    @Column(name = "price")
    @NotNull
    private Integer price;
    
    @Column(name = "image_bundle_name", length = 50)
    @NotNull
    private String imageBundleName;
}
