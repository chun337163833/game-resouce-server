package org.shovelgame.game.domain;

import java.math.BigDecimal;

public class ChanceEvaluator {

	public static boolean success(double val) {
		if(val >= 1.) return true;
		return Math.random() <= val;
	}
	public static boolean success(BigDecimal val) {
		return success(val.doubleValue());
	}
	

}
