package org.shovelgame.game.domain.model.reward;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Seeker;
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
	public void claim(Player player) {
		Seeker seeker = new Seeker();
		seeker.setSeekerModel(this.seeker);
		seeker.setOwner(player);
		seeker.persist();
	}
}
