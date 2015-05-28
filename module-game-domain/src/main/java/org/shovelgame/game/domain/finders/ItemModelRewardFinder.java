package org.shovelgame.game.domain.finders;

import java.util.List;
import java.util.Random;

import org.shovelgame.game.domain.enumeration.ItemType;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.model.ItemModel;

public abstract class ItemModelRewardFinder extends AbstractRewardFinder<ItemModel> {

	@Override
	public ItemModel findByRarity(Rarity r) {
		List<ItemModel> models = ItemModel.findItemModelsByRarityAndType(r, getItemType()).getResultList();
		int i = models.size();
		return models.get(new Random().nextInt(i));
	}

	public abstract ItemType getItemType();

}
