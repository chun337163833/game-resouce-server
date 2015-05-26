package org.shovelgame.game.domain.model.reward;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.model.MinionModel;
import org.shovelgame.game.domain.model.MissionReward;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(versionField = "")
@RooJavaBean
@DiscriminatorValue("Minion")
public class MinionReward extends MissionReward {

	@ManyToOne
	@JoinColumn(name = "minion", referencedColumnName = "id")
	private MinionModel minion;

	@Override
	public void claim(RewardClaim claim) {
		Minion minion = new Minion();
		minion.setMinionModel(this.minion);
		minion.setOwner(claim.getPlayer());
		minion.persist();
		claim.remove();
	}
}
