package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "seekerSpecializations", "qualityGrade" })
@RooJpaActiveRecord(versionField = "", table = "seeker_model", schema = "model", sequenceName = "model.seeker_model_id_seq")
public class SeekerModel {
}
