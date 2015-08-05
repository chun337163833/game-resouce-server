package org.shovelgame.game.domain.model.reward;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.model.MissionReward;
import org.shovelgame.game.domain.model.SeekerModel;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(versionField="")
@RooJavaBean
@DiscriminatorValue("Seeker")
public class SeekerReward extends MissionReward {

	@ManyToOne
	@JoinColumn(name = "seeker", referencedColumnName = "id")
	private SeekerModel seeker;
	
	@Override
	public void claim(RewardClaim claim) {
		this.seeker.claim(claim.getPlayer());
		claim.remove();
	}
}
