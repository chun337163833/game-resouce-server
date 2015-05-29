package org.shovelgame.game.domain.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.leveling.Level;

public enum Rarity {

	Common(1, 24, 0.8, 1.), Legend(Level.MAX / 2, 12, 0.3, 1.5), Ancient(Level.MAX, 4, 0.1, 2.);

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
	
	private double expMultiplier;
	
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

	public static Rarity[] getPossibleRarityForSearch(int level) {
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
	
	public static List<Rarity> sortByChance(Rarity[] rarities) {
		List<Rarity> r = new ArrayList<>(Arrays.asList(rarities));
		Collections.sort(r, new Comparator<Rarity>() {
			@Override
			public int compare(Rarity o1, Rarity o2) {
				double c1 = o1.getChanceToFind();
				double c2 = o2.getChanceToFind();
				if(c1 < c2) {
					return -1;
				} else if(c1 > c2) {
					return 1;
				}
				return 0;
			}
		});
		return r;
	}
	
	
	public static Rarity resolveRarityForSeekerReward(int level) {
		Rarity[] rars = Rarity.getPossibleRarityForSearch(level);
		List<Rarity> sorted = Rarity.sortByChance(rars);
		for(Rarity r: sorted) {
			if(ChanceEvaluator.success(r.getChanceToFind())) {
				return r;
			}
		}
		return null;
	}
}
