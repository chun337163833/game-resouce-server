package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.enumeration.ItemType;

public class WeaponModelRewardFinder extends ItemModelRewardFinder {

	@Override
	public ItemType getItemType() {
		return ItemType.Weapon;
	}
	
}
