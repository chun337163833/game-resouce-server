package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "player", "reward" })
@RooJpaActiveRecord(versionField = "", table = "reward_claim", schema = "data", sequenceName = "data.reward_claim_id_seq")
public class RewardClaim {
}
