package org.shovelgame.engine.leveling.service;

import java.util.Map;

import org.shovelgame.game.domain.leveling.Level;
import org.shovelgame.game.domain.leveling.LevelingService;

public abstract class BasicLevelingAlgorithmService implements LevelingService {

	@Override
	public void experienceChanged(Level level) {
		Map<Integer, Long> experienceMap = getExperienceMap();
		Long cap = experienceMap.get(Level.MAX - 1);
		if(level.getExperience() > cap) {
			level.setExperience(cap);
			return;
		}
		Long currentExperience = level.getExperience();
		for (int i = level.getLevel(); i <= Level.MAX; i++) {
			Long exp = experienceMap.get(i);
			if(currentExperience >= exp) {
				level.setLevel(i + 1);
			} else {
				break;
			}
		}
	}	
	@Override
	public long calculateExperience(int level, double perc) {
		if(level == Level.MAX) return 0L;
		long exp = getExperienceMap().get(level);
		long nexp = getExperienceMap().get(level + 1);
		return (long) ((nexp - exp) * perc);
	}
}
