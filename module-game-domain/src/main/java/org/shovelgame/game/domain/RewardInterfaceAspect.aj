package org.shovelgame.game.domain;

import org.shovelgame.game.domain.finders.Reward;

public aspect RewardInterfaceAspect {
	declare parents: @RewardInterface * implements Reward;
}
