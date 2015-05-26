package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = RewardClaimPK.class, versionField = "", table = "reward_claim", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "player", "reward" })
public class RewardClaim {
}
