package org.shovelgame.game.domain.finders;

import java.util.List;
import java.util.Random;

import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.model.SeekerModel;

public class SeekerModelRewardFinder extends AbstractRewardFinder<SeekerModel> {

	@Override
	public SeekerModel findByRarity(Rarity r) {
		List<SeekerModel> models = SeekerModel.findSeekerModelsByRarity(r).getResultList();
		int i = models.size();
		return models.get(new Random().nextInt(i));
	}

}
