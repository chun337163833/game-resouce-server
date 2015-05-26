package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Transient;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "seeker_model", schema = "model", sequenceName = "model.seeker_model_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "missionRewards", "seekerSpecializations" })
public class SeekerModel {

    @Transient
    private Set<MissionReward> missionRewards;
}
