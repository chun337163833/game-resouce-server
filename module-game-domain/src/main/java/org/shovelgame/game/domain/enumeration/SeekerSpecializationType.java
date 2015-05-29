package org.shovelgame.game.domain.enumeration;

import org.shovelgame.game.domain.finders.ArmorModelRewardFinder;
import org.shovelgame.game.domain.finders.MinionModelRewardFinder;
import org.shovelgame.game.domain.finders.Reward;
import org.shovelgame.game.domain.finders.SeekerModelRewardFinder;
import org.shovelgame.game.domain.finders.RewardFinder;
import org.shovelgame.game.domain.finders.TalismanModelRewardFinder;
import org.shovelgame.game.domain.finders.TestRewardFinder;
import org.shovelgame.game.domain.finders.WeaponModelRewardFinder;

public enum SeekerSpecializationType {
	Gold(PlayerResource.Gold), 
	Diamond(PlayerResource.Diamond), 
	Seeker(SeekerModelRewardFinder.class), 
	Minion(MinionModelRewardFinder.class), 
	Armor(ArmorModelRewardFinder.class), 
	Weapon(WeaponModelRewardFinder.class), 
	Talisman(TalismanModelRewardFinder.class),
	Test(TestRewardFinder.class);
	private Class<? extends RewardFinder<? extends Reward>> finder;
	private PlayerResource resource;
	
	private SeekerSpecializationType(PlayerResource resource) {
		this.resource = resource;
	}

	private SeekerSpecializationType() {
		this.finder = null;
	}

	private SeekerSpecializationType(
			Class<? extends RewardFinder<? extends Reward>> modelClass) {
		this.finder = modelClass;
	}

	public Class<? extends RewardFinder<? extends Reward>> getFinder() {
		return finder;
	}
	
	public PlayerResource getResource() {
		return resource;
	}
	
}
