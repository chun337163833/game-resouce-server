package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.data.RewardClaim;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJpaActiveRecord(inheritanceType = "SINGLE_TABLE", versionField = "", table = "mission_reward", schema = "model", sequenceName = "model.mission_reward_id_seq")
@RooDbManaged(automaticallyDelete = false)
@DiscriminatorColumn(name = "dtype")
@RooToString(excludeFields = { "item", "minion", "mission", "seeker", "rewardClaims", "players" })
public abstract class MissionReward {

    @Column(name = "dtype", length = 100, insertable = false, updatable = false)
    @NotNull
    private String dtype;

    @Transient
    private SeekerModel seeker;

    @Transient
    private ItemModel item;

    @Transient
    private MinionModel minion;

    @Transient
    private Integer value;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public abstract void claim(RewardClaim claim);
}
