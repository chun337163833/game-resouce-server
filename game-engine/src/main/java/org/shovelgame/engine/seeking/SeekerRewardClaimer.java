package org.shovelgame.engine.seeking;

import javax.transaction.Transactional;

import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.enumeration.ResourceGeneratorData;
import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;
import org.shovelgame.game.domain.finders.Reward;
import org.shovelgame.game.domain.finders.RewardFinder;
import org.shovelgame.game.domain.leveling.Levelable;

@Logger
public class SeekerRewardClaimer {

	@Transactional
	public SeekerRewardDetail claim(Seeker seeker) {
		SeekerSpecializationType spec = seeker.getSeekerModel()
				.getSpecialization();
		Player p = seeker.getOwner();
		SeekerRewardDetail detail = null;
		try {
			if (spec.getResource() != null) {
				// reward is resource
				int level = seeker.getLevel();
				ResourceGeneratorData data = spec.getResource().getGenData();
				int baseValue = data.randomValue(level);
				long value = calculateResourceValue(level, baseValue, seeker.getSeekerModel().getRarity());
				p.addResource(spec.getResource(), value);
				detail = new SeekerRewardDetail(spec, value);
				String serviceName = Seeker.class.getAnnotation(Levelable.class).service();
				long experience = data.calculateExperienceForSeeker(level, baseValue, serviceName);
				seeker.addExperience(experience);
			} else {
				RewardFinder<? extends Reward> finder = spec.getFinder().newInstance();
				Reward reward = finder.find(seeker);
				if (reward != null) {
					reward.claim(p);
					detail = new SeekerRewardDetail(spec, reward.getId());
				}
				Rarity r = reward.getRarity();
			}
			seeker.setStartedSearchTime(null);
		} catch (Exception e) {
			log.error("", e);
		}
		return detail;
	}

	public long calculateResourceValue(int level, int baseValue, Rarity rarity) {
		return (long) (baseValue * rarity.getResourceMultiplier());
	}

}
