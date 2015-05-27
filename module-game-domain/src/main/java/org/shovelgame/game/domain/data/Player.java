package org.shovelgame.game.domain.data;
import org.shovelgame.game.domain.enumeration.PlayerResource;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "player", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "minions", "teams", "seekers", "rewardClaims", "missionRewards" })
public class Player {

    public void addResource(PlayerResource res, long value) {
        if (PlayerResource.Gold.equals(res)) {
            setGolds(getGolds() + value);
        } else if (PlayerResource.Diamond.equals(res)) {
            setGolds(getDiamonds() + value);
        }
    }
}
