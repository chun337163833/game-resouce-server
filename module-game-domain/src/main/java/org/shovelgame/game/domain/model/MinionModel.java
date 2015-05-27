package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.finders.SeekerReward;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model", sequenceName = "model.minion_model_id_seq")
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits", "attributes", "qualityGrade", "minionAttributes", "specialization", "missionRewardMinions", "missionRewards" })
public class MinionModel implements SeekerReward {

    @Transient
    private Set<MissionReward> missionRewards;

    @Column(name = "rarity", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
}
