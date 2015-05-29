package org.shovelgame.game.domain.leveling;

import java.util.Map;

public interface LevelingService {

	void experienceChanged(Level level);
	long calculateExperience(int level, double perc);
	Map<Integer, Long> getExperienceMap();
}
