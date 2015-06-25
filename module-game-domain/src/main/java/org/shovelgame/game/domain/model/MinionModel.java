package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.RewardInterface;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.enumeration.MinionSpecialization;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(finders = { "findMinionModelsByRarity" }, versionField = "", table = "minion_model", schema = "model", sequenceName = "model.minion_model_id_seq")
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits", "attributes", "qualityGrade", "minionAttributes", "specialization", "missionRewardMinions", "missionRewards" })
@RewardInterface
public class MinionModel {

    @Transient
    private Set<MissionReward> missionRewards;

    @Column(name = "rarity", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @Column(name = "specialization", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MinionSpecialization specialization;

    @Transactional
    @Override
    public void claim(Player player) {
        Minion m = new Minion();
        m.setMinionModel(this);
        m.setOwner(player);
        m.persist();
    }
}
