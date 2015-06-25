package org.shovelgame.game.domain.enumeration;

public enum TraitAlgorithm {

	Decrease(true), Increase(false);
	
	private boolean isForOpponent;
	
	private TraitAlgorithm(boolean isForOpponent) {
		this.isForOpponent = isForOpponent;
	}

	public boolean isForOpponent() {
		return this.isForOpponent;
	}
	
}
