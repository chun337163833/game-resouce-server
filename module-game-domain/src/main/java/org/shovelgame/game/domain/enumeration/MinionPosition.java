package org.shovelgame.game.domain.enumeration;

import java.util.HashSet;
import java.util.Set;

public enum MinionPosition {
	Leader, Bot, Top, Mid;
	
	
	public static MinionPosition[] valueOf(String[] values) {
		Set<MinionPosition> positions = new HashSet<>();
		for(String value: values) {
			positions.add(MinionPosition.valueOf(value));
		}
		return positions.toArray(new MinionPosition[positions.size()]);
	}
	
}
