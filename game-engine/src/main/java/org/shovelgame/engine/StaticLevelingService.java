package org.shovelgame.engine;

import java.util.HashMap;
import java.util.Map;

import org.shovelgame.game.domain.leveling.Level;
import org.shovelgame.game.domain.leveling.LevelingService;

public class StaticLevelingService implements LevelingService {

	public static Map<Integer, Long> experienceMap = new HashMap<>();
	private static int baseExp = 10;
	static {
		long prev = 0;
		for (int i = 1; i <= Level.MAX; i++) {
			long value = (long) i * baseExp + prev;
			experienceMap.put(i, value);
			prev = value;
			System.out.println(i + ":" + value);
		}
	}

	@Override
	public void experienceChanged(Level level) {
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

}
