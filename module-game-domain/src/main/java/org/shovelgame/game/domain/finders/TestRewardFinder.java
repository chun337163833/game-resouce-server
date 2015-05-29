package org.shovelgame.game.domain.finders;

import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.enumeration.Rarity;

public class TestRewardFinder extends AbstractRewardFinder<Reward> {

	@Override
	public Reward findByRarity(final Rarity r) {
		return new Reward() {
			
			@Override
			public Rarity getRarity() {
				return r;
			}
			
			@Override
			public Long getId() {
				return 1L;
			}
			
			@Override
			public void claim(Player player) {
				
				
			}
		};
	}

}
