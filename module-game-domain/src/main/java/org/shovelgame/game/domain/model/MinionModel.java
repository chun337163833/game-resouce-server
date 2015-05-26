package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Transient;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model", sequenceName = "model.minion_model_id_seq")
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits", "attributes", "qualityGrade", "minionAttributes", "specialization", "missionRewardMinions", "missionRewards" })
public class MinionModel {

    @Transient
    private Set<MissionReward> missionRewards;
}
