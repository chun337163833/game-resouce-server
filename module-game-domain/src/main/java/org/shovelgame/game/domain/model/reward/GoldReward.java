package org.shovelgame.game.domain.model.reward;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.model.MissionReward;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(versionField="")
@RooJavaBean
@DiscriminatorValue("Gold")
public class GoldReward extends MissionReward {

	@Column(name = "value")
    private Integer golds;
	
	@Override
	public void claim(RewardClaim claim) {
		claim.remove();
	}
}
