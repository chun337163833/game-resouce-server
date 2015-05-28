package org.shovelgame.game.domain.finders;

import java.util.List;
import java.util.Random;

import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.model.MinionModel;

public class MinionModelRewardFinder extends AbstractRewardFinder<MinionModel> {

	@Override
	public MinionModel findByRarity(Rarity r) {
		List<MinionModel> models = MinionModel.findMinionModelsByRarity(r).getResultList();
		int i = models.size();
		return models.get(new Random().nextInt(i));
	}

}
