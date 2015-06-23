package org.shovelgame.game.domain.data;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.shovelgame.game.domain.enumeration.PlayerResource;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(finders = { "findPlayersByUserName" }, versionField = "", table = "player", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "minions", "teams", "seekers", "rewardClaims", "missionRewards", "rightss", "team" })
public class Player {

    @OneToOne
    @JoinColumn(name = "team", referencedColumnName = "id", nullable = false)
    private Team team;

    public void addResource(PlayerResource res, long value) {
        if (PlayerResource.Gold.equals(res)) {
            setGolds(getGolds() + value);
        } else if (PlayerResource.Diamond.equals(res)) {
            setGolds(getDiamonds() + value);
        }
    }

    public double getCurrentExperienceBoost() {
        if (getExperienceBoostExpire() == null || getExperienceBoostExpire().before(new Date())) {
            return 0d;
        }
        return getExperienceBoost().doubleValue();
    }
}
