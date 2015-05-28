package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.RewardInterface;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(finders = { "findSeekerModelsByRarity" }, versionField = "", table = "seeker_model", schema = "model", sequenceName = "model.seeker_model_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "missionRewards", "seekerSpecializations", "seekers" })
@RewardInterface
public class SeekerModel {

    @Transient
    private Set<MissionReward> missionRewards;

    @Column(name = "rarity", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @Column(name = "specialization", length = 10)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SeekerSpecializationType specialization;

    @Transactional
    public void claim(Player player) {
        Seeker seeker = new Seeker();
        seeker.setSeekerModel(this);
        seeker.setOwner(player);
        seeker.persist();
    }
}
