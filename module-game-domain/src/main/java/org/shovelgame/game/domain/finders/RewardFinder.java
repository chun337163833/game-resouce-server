package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Seeker;

public interface RewardFinder<E extends Reward> {

	E find(Seeker seeker);

}
