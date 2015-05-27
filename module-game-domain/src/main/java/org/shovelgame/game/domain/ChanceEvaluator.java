package org.shovelgame.game.domain;

import java.math.BigDecimal;

public class ChanceEvaluator {

	public static boolean success(double val) {
		return false;
	}
	public static boolean success(BigDecimal val) {
		return success(val.doubleValue());
	}
	

}
