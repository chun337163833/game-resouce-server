package org.shovelgame.game.domain.enumeration;

import java.math.BigDecimal;

public enum TraitType {

	Percentage, Addition;
	
	public BigDecimal add(BigDecimal value, BigDecimal power) {
		if(Percentage.equals(this)) {
			return value.divide(BigDecimal.valueOf(100)).multiply(power);
		}
		return value;
	}
}
