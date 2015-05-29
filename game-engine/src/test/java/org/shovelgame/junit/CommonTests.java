package org.shovelgame.junit;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.shovelgame.engine.leveling.service.HighExperienceMapService;
import org.shovelgame.engine.leveling.service.LowExperienceMapService;
import org.shovelgame.engine.seeking.SeekerRewardClaimer;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;
import org.shovelgame.game.domain.leveling.Level;
import org.shovelgame.game.domain.leveling.Levelable;
import org.shovelgame.game.domain.leveling.LevelingService;
import org.shovelgame.game.domain.leveling.LevelingServiceAccessor;
import org.shovelgame.game.domain.leveling.LevelingServiceProvider;
import org.shovelgame.game.domain.model.SeekerModel;

public class CommonTests {
	
	@BeforeClass 
	public static void setup() {
		LevelingServiceAccessor accesor = new LevelingServiceAccessor();
		accesor.setProvider(new LevelingServiceProvider() {
			@Override
			public LevelingService getService(String name) {
				if(name.startsWith("high")) {
					return new HighExperienceMapService();
				}
				return new LowExperienceMapService();
			}
		});
		
	}
	
	@Test
	public void testSeekerResourceRewardClaim() {
		SeekerRewardClaimer claimer = new SeekerRewardClaimer();
		Seeker s = new Seeker();
		SeekerModel model = new SeekerModel();
		model.setRarity(Rarity.Common);
		model.setSpecialization(SeekerSpecializationType.Test);
		s.setSeekerModel(model);
		Player p = new Player();
		p.setExperienceBoost(BigDecimal.valueOf(1.5));
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.DATE, 1);
		p.setExperienceBoostExpire(exp.getTime());
		p.setGolds(0L);
		p.setDiamonds(0L);
		s.setOwner(p);
		s.setLevel(1);
		s.setExperience(0L);
		
		for (int i = 1; i <= 30; i++) {
			claimer.claim(s);
		}
		System.out.println(s.getLevel());
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
	public void testLevelingMinion() {
		Minion seeker = new Minion();
		seeker.setLevel(1);
		int fromLevel = Level.MAX -1;
		seeker.setExperience(LevelingServiceAccessor.getLevelingService(Minion.class.getAnnotation(Levelable.class).service()).getExperienceMap().get(fromLevel) - 1);
		Assert.assertEquals(Integer.valueOf(fromLevel), seeker.getLevel());
	}
	
	@Test
	public void testLevelingSeeker() {
		Seeker seeker = new Seeker();
		seeker.setLevel(1);
		int fromLevel = Level.MAX -1;
		seeker.setExperience(LevelingServiceAccessor.getLevelingService(Seeker.class.getAnnotation(Levelable.class).service()).getExperienceMap().get(fromLevel) - 1);
		Assert.assertEquals(Integer.valueOf(fromLevel), seeker.getLevel());
	}

}
