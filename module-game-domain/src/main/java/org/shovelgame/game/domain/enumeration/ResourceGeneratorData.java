package org.shovelgame.game.domain.enumeration;

import java.util.Random;

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
		int diff = (max - min);
		int lvlDiff = diff * level;
		int lvlMin = min + lvlDiff - diff;
		int lvlMax = max + lvlDiff - diff;
		int min = (int)(lvlMin * multiplier);
		int max = (int)(lvlMax * multiplier);
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum == 0? 1: randomNum;
	}
}
