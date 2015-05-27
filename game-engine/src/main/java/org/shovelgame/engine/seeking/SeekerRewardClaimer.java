package org.shovelgame.engine.seeking;

import javax.transaction.Transactional;

import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Seeker;
import org.shovelgame.game.domain.enumeration.Rarity;
import org.shovelgame.game.domain.enumeration.ResourceGeneratorData;
import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;

public class SeekerRewardClaimer {

	@Transactional
	public SeekerRewardDetail claim(Seeker seeker) {
        SeekerSpecializationType spec = seeker.getSeekerModel().getSpecialization();
        if (spec.getResource() != null) {
            //reward is resource
        	Player p = seeker.getOwner();
            long value = calculateResourceValue(seeker.getLevel(), spec.getResource().getGenData(), seeker.getSeekerModel().getRarity());
            p.addResource(spec.getResource(), value);
            return new SeekerRewardDetail(spec, value);
        } else {
        	
        }
        return null;
    }
	
	public long calculateResourceValue(int level, ResourceGeneratorData data, Rarity rarity) {
		int baseValue = data.randomValue(level);
		return baseValue;
	}

}
