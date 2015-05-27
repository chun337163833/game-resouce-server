package org.shovelgame.game.domain.enumeration;

import java.util.ArrayList;
import java.util.List;

import org.shovelgame.game.domain.ChanceEvaluator;

public enum Rarity {

	Common(1, 24, 0.8, 1.), Legend(20, 12, 0.3, 1.5), Ancient(30, 4, 0.1, 2.);

	/**
	 * Required seeker level to find this rarity type
	 */
	private int seekerLevel;

	/**
	 * Base search time of seeker with this rarity in hours.
	 */
	private int seekerSearchTime;

	/**
	 * Determine chance to find when seeker can searching for type if this rarity.
	 */
	private double chanceToFind;

	/**
	 * Multiplier for resource reward founded by seeker.
	 */
	private double resourceMultiplier;

	private Rarity(int seekerLevel, int seekerSearchTime, double chanceToFind, double resourceMultiplier) {
		this.seekerLevel = seekerLevel;
		this.seekerSearchTime = seekerSearchTime;
		this.chanceToFind = chanceToFind;
		this.resourceMultiplier = resourceMultiplier;
	}
	
	public double getResourceMultiplier() {
		return resourceMultiplier;
	}
	
	public int getSeekerLevel() {
		return seekerLevel;
	}

	public int getSeekerSearchTime() {
		return seekerSearchTime;
	}

	public double getChanceToFind() {
		return chanceToFind;
	}

	public Rarity[] getPossibleRarityForSearch(int level) {
		List<Rarity> rars = new ArrayList<>();
		for (Rarity r : Rarity.values()) {
			if (r.getSeekerLevel() <= level) {
				rars.add(r);
			}
		}
		return rars.toArray(new Rarity[rars.size()]);
	}

	public boolean searchSuccess() {
		return ChanceEvaluator.success(chanceToFind);
	}
	
}
