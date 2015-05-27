package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Seeker;

public interface SeekerRewardFinder<E extends SeekerReward> {

	E find(Seeker seeker);

}
