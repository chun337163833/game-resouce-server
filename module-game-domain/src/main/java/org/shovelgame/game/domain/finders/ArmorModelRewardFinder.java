package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.enumeration.ItemType;

public class ArmorModelRewardFinder extends ItemModelRewardFinder {

	@Override
	public ItemType getItemType() {
		return ItemType.Armor;
	}

}
