package org.shovelgame.game.domain.model.reward;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.model.ItemModel;
import org.shovelgame.game.domain.model.MissionReward;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJpaActiveRecord(versionField="")
@RooJavaBean
@DiscriminatorValue("Item")
public class ItemReward extends MissionReward {

	@ManyToOne
	@JoinColumn(name = "item", referencedColumnName = "id")
	private ItemModel item;

	@Override
	public void claim(RewardClaim claim) {
		this.item.claim(claim.getPlayer());
		claim.remove();
	}
	
}
