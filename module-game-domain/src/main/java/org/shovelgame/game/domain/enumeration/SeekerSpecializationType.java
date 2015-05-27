package org.shovelgame.game.domain.enumeration;

import org.shovelgame.game.domain.finders.ArmorModelRewardFinder;
import org.shovelgame.game.domain.finders.MinionModelRewardFinder;
import org.shovelgame.game.domain.finders.SeekerModelRewardFinder;
import org.shovelgame.game.domain.finders.SeekerRewardFinder;
import org.shovelgame.game.domain.finders.TalismanModelRewardFinder;
import org.shovelgame.game.domain.finders.WeaponModelRewardFinder;

public enum SeekerSpecializationType {
	Gold(PlayerResource.Gold), 
	Diamond(PlayerResource.Diamond), 
	Seeker(SeekerModelRewardFinder.class), 
	Minion(MinionModelRewardFinder.class), 
	Armor(ArmorModelRewardFinder.class), 
	Weapon(WeaponModelRewardFinder.class), 
	Talisman(TalismanModelRewardFinder.class);
	private Class<? extends SeekerRewardFinder<?>> finder;
	private PlayerResource resource;
	
	private SeekerSpecializationType(PlayerResource resource) {
		this.resource = resource;
	}

	private SeekerSpecializationType() {
		this.finder = null;
	}

	private SeekerSpecializationType(
			Class<? extends SeekerRewardFinder<?>> modelClass) {
		this.finder = modelClass;
	}

	public Class<? extends SeekerRewardFinder<?>> getFinder() {
		return finder;
	}
	
	public PlayerResource getResource() {
		return resource;
	}
	
}
