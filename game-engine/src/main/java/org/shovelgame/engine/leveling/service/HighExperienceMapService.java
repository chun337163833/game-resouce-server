package org.shovelgame.engine.leveling.service;

import java.util.HashMap;
import java.util.Map;

import org.shovelgame.game.domain.leveling.Level;

public class HighExperienceMapService extends BasicLevelingAlgorithmService {
	private static Map<Integer, Long> experienceMap = new HashMap<>();
	private static int baseExp = 600;
	static {
		long prev = 0;
		double xpcoeff = 1.;
		for (int i = 1; i <= Level.MAX; i++) {
			if(i % 5 == 0) {
				xpcoeff += 0.05;
			}
			long value = (long) ((i * baseExp + prev) * xpcoeff);
			experienceMap.put(i, value);
			prev = value;
			System.out.println(i + ":" + value);
		}
	}
	
	@Override
	public Map<Integer, Long> getExperienceMap() {
		return experienceMap;
	}

	

}
