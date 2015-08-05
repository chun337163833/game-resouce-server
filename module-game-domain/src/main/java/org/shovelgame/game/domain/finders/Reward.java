package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.enumeration.Rarity;

public interface Reward {

	Long getId();
	Rarity getRarity();
	void claim(Player player);
}
