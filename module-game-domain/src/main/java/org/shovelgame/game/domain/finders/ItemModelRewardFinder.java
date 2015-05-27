package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.ItemType;
import org.shovelgame.game.domain.model.ItemModel;

public abstract class ItemModelRewardFinder implements SeekerRewardFinder<ItemModel> {

	@Override
	public ItemModel find(Seeker seeker) {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract ItemType getItemType();

}
