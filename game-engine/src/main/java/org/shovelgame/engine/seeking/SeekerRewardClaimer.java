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
				long value = calculateResourceValue(seeker.getLevel(), spec
						.getResource().getGenData(), seeker.getSeekerModel()
						.getRarity());
				p.addResource(spec.getResource(), value);
				detail = new SeekerRewardDetail(spec, value);
			} else {
				RewardFinder<? extends Reward> finder = spec.getFinder().newInstance();
				Reward reward = finder.find(seeker);
				if (reward != null) {
					reward.claim(p);
					detail = new SeekerRewardDetail(spec, reward.getId());
				}
			}
//			seeker.set
			seeker.setStartedSearchTime(null);
		} catch (Exception e) {
			log.error("", e);
		}
		return detail;
	}

	public long calculateResourceValue(int level, ResourceGeneratorData data,
			Rarity rarity) {
		int baseValue = data.randomValue(level);
		return (long) (baseValue * rarity.getResourceMultiplier());
	}

}
