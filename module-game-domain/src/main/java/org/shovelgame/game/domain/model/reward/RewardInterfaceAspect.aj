package org.shovelgame.game.domain.model.reward;

import org.shovelgame.game.domain.finders.Reward;

public aspect RewardInterfaceAspect {
	declare parents: @RewardInterface * implements Reward;
}
