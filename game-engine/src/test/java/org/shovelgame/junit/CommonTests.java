package org.shovelgame.junit;

import org.junit.Test;
import org.shovelgame.engine.seeking.SeekerRewardClaimer;
import org.shovelgame.game.domain.enumeration.PlayerResource;
import org.shovelgame.game.domain.enumeration.Rarity;

public class CommonTests {

	@Test
	public static void testSeekerRewardClaim() {
		for (int i = 1; i <= 30; i++) {
			long l = new SeekerRewardClaimer().calculateResourceValue(i, PlayerResource.ActionPoint.getGenData(), Rarity.Ancient);
			System.out.println(l);
		}
	}

}
