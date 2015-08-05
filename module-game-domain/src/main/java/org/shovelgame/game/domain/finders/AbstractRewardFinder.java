package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.Rarity;

public abstract class AbstractRewardFinder<E extends Reward> implements RewardFinder<E> {

	@Override
	public E find(Seeker seeker) {
		Rarity r = Rarity.resolveRarityForSeekerReward(seeker.getLevel());
		if(r == null) {
			return null;
		}
		return findByRarity(r);
	}

	public abstract E findByRarity(Rarity r);

}
