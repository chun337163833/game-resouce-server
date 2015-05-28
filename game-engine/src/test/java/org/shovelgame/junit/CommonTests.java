package org.shovelgame.junit;

import org.junit.Assert;
import org.junit.Test;
import org.shovelgame.engine.StaticLevelingService;
import org.shovelgame.engine.seeking.SeekerRewardClaimer;
import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.PlayerResource;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.leveling.Level;
import org.shovelgame.game.domain.leveling.LevelingServiceAccessor;

public class CommonTests {

	@Test
	public void testSeekerRewardClaim() {
		for (int i = 1; i <= 30; i++) {
			long l = new SeekerRewardClaimer().calculateResourceValue(i, PlayerResource.Diamond.getGenData(), Rarity.Ancient);
			System.out.println(l);
		}
	}

	@Test
	public void testSortRarities() {
		int ancient = 0;
		int common = 0;
		int legend = 0;
		int nothing = 0;
		for(int i = 0; i < 100; i++) {
			Rarity r = Rarity.resolveRarityForSeekerReward(5);
			if(r == null) {
				nothing++;
			} else if(Rarity.Common.equals(r)) {
				common++;
			} else if(Rarity.Legend.equals(r)) {
				legend++;
			} else if(Rarity.Ancient.equals(r)) {
				ancient++;
			}
		}
		System.out.println("Ancient: " + ancient);
		System.out.println("Legend: " + legend);
		System.out.println("Common: " + common);
		System.out.println("Nothing: " + nothing);
		
	}
	@Test
	public void testLeveling() {
		LevelingServiceAccessor accesor = new LevelingServiceAccessor();
		accesor.setService(new StaticLevelingService());
		Seeker seeker = new Seeker();
		seeker.setLevel(1);
		int fromLevel = Level.MAX -1;
		seeker.setExperience(StaticLevelingService.experienceMap.get(fromLevel) - 1);
		Assert.assertEquals(Integer.valueOf(fromLevel), seeker.getLevel());
	}
	
}
