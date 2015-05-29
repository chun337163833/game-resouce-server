package org.shovelgame.game.domain.enumeration;

import java.util.Random;

import org.shovelgame.game.domain.leveling.LevelingService;
import org.shovelgame.game.domain.leveling.LevelingServiceAccessor;

public class ResourceGeneratorData {

	private final int min;
	private final int max;
	private final double multiplier;

	public ResourceGeneratorData(int min, int max, double multiplier) {
		super();
		this.min = min;
		this.max = max;
		this.multiplier = multiplier;
	}

	public long getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public double getMultiplier() {
		return multiplier;
	}
	
	public int randomValue(int level) {
		int[] minmax = getMinMax(level);
		int min = minmax[0];
		int max = minmax[1];
		
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum == 0? 1: randomNum;
	}
	public int getMinByLevel(int level) {
		int[] minmax = getMinMax(level);
		return minmax[0];
	}
	private int[] getMinMax(int level) {
		int diff = (max - min);
		int lvlDiff = diff * level;
		int lvlMin = min + lvlDiff - diff;
		int lvlMax = max + lvlDiff - diff;
		int min = (int)(Math.floor(lvlMin * multiplier));
		int max = (int)(Math.ceil(lvlMax * multiplier));
		return new int[]{min, max};
	}
	
	public long calculateExperienceForSeeker(int level, int value, String serviceName) {
		int[] minmax = getMinMax(level);
		int min = minmax[0];
		int max = minmax[1];
		int res = max - min;
		int val = value - min;
		double perc = (double) val / (double) res;
		if(value == min) {
			perc = .1;
		} else if(value == max) {
			perc = .9;
		}
		
		LevelingService service = LevelingServiceAccessor.getLevelingService(serviceName);
		return service.calculateExperience(level, perc);
	}
	
}
